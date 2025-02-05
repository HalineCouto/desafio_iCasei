package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.FavoritesList
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Playlist
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Snippet
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Thumbnail
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Thumbnails
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.VideoId
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.model.FavoriteVideo
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.AddFavoriteVideoUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.AddPlaylistUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.AddVideoToPlaylistUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.GetFavoriteVideosUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.GetPlaylistsUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.GetVideosForPlaylistUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.IsFavoriteVideoUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.RemoveFavoriteVideoUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.RemoveVideoFromPlaylistUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.launch
import java.util.UUID

class LocalYouTubeViewModel(
    private val addFavoriteUseCase: AddFavoriteVideoUseCase,
    private val getFavoritesUseCase: GetFavoriteVideosUseCase,
    private val addVideoToPlaylistUseCase: AddVideoToPlaylistUseCase,
    private val getPlaylistsUseCase: GetPlaylistsUseCase,
    private val removeFavoriteVideoUseCase: RemoveFavoriteVideoUseCase,
    private val isFavoriteVideoUseCase: IsFavoriteVideoUseCase,
    private val addPlaylistUseCase: AddPlaylistUseCase,
    private val getVideosForPlaylistUseCase: GetVideosForPlaylistUseCase,
    private val removeVideoFromPlaylistUseCase: RemoveVideoFromPlaylistUseCase

) : ViewModel() {

    private val _favoriteVideos = MutableStateFlow<List<Video>>(emptyList())
    val favoriteVideos: StateFlow<List<Video>> = _favoriteVideos

    private val _playlists = MutableStateFlow<List<Playlist>>(emptyList())
    val playlists: StateFlow<List<Playlist>> = _playlists

    init {
        loadFavoriteVideos()
        loadPlaylists()
    }

    private fun loadFavoriteVideos() {
        viewModelScope.launch {
            val favoriteEntities = getFavoritesUseCase.invoke()
            val videos = favoriteEntities.map { entity ->
                async { getVideoById(entity) }
            }.awaitAll()
            _favoriteVideos.value = videos
        }
    }

    private fun getVideoById(favoriteVideo: FavoriteVideoEntity): Video {
        return Video(
            id = VideoId(favoriteVideo.videoId),
            snippet = Snippet(
                title = favoriteVideo.title,
                description = favoriteVideo.description,
                thumbnails = Thumbnails(default = Thumbnail(favoriteVideo.thumbnailUrl))
            )
        )
    }

    private fun loadPlaylists() {
        viewModelScope.launch {
            getPlaylistsUseCase().collect { playlistEntities ->
                val playlists = playlistEntities.map { playlistEntity ->
                    val videos = getVideosForPlaylistUseCase(playlistEntity.playlistId)
                    Playlist(
                        playlistId = playlistEntity.playlistId,
                        name = playlistEntity.name,
                        videos = mapVideosForPlayList(videos)
                    )
                }
                _playlists.value = playlists
            }
        }
    }

    private fun mapVideosForPlayList(
        videosForPlaylistUseCase: List<FavoriteVideoEntity>
    ):
            List<FavoritesList> {
        return videosForPlaylistUseCase.map { video ->
            FavoritesList(
                videoId = video.videoId,
                title = video.title,
                description = video.description,
                thumbnailUrl = video.thumbnailUrl
            )
        }
    }


    fun addFavorite(video: FavoriteVideo) {
        viewModelScope.launch {
            val isAlreadyFavorite = isFavoriteVideoUseCase(video.videoId).first()
            if (!isAlreadyFavorite) {
                addFavoriteUseCase(
                    FavoriteVideoEntity(
                        videoId = video.videoId,
                        title = video.title,
                        thumbnailUrl = video.thumbnailUrl,
                        description = video.description
                    )
                )
            }
            loadFavoriteVideos()
        }
    }


    fun removeFavorite(video: FavoriteVideo) {
        viewModelScope.launch {
            removeFavoriteVideoUseCase(video.videoId)
            loadFavoriteVideos()
        }
    }

    fun isFavorite(videoId: String): Flow<Boolean> {
        return isFavoriteVideoUseCase(videoId)
    }


    fun addVideoToPlaylist(playlistName: String, video: FavoriteVideo) {
        viewModelScope.launch {
            val playlistId = UUID.randomUUID().toString()
            addPlaylistUseCase(
                PlaylistEntity(
                    playlistId = playlistId,
                    name = playlistName
                )
            )

            addFavoriteUseCase(
                FavoriteVideoEntity(
                    videoId = video.videoId,
                    title = video.title,
                    description = video.description,
                    thumbnailUrl = video.thumbnailUrl
                )
            )

            addVideoToPlaylistUseCase(
                playlistId = playlistId,
                videoId = video.videoId,
                title = video.title,
                description = video.description,
                thumbnailUrl = video.thumbnailUrl
            )
        }
    }

    fun getVideosForPlaylist(playlistId: String): StateFlow<List<Video>> {
        val videosFlow =
            MutableStateFlow<List<Video>>(
                emptyList()
            )

        viewModelScope.launch {
            val playList = getVideosForPlaylistUseCase(playlistId)
            val videos = playList.map { favorite ->
                Video(
                    id = VideoId(favorite.videoId),
                    snippet = Snippet(
                        title = favorite.title,
                        description = favorite.description,
                        thumbnails = Thumbnails(default = Thumbnail(favorite.thumbnailUrl))
                    )
                )
            }
            videosFlow.value = videos
        }

        return videosFlow
    }

    fun removeVideoFromPlaylist(playlistId: String, videoId: String) {
        viewModelScope.launch {
            try {
                removeVideoFromPlaylistUseCase(playlistId, videoId)
                getVideosForPlaylist(playlistId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

