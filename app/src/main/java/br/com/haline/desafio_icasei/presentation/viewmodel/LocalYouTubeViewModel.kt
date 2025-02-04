package br.com.haline.desafio_icasei.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.haline.desafio_icasei.data.dataclass.FavoritesList
import br.com.haline.desafio_icasei.data.dataclass.Playlist
import br.com.haline.desafio_icasei.data.dataclass.Snippet
import br.com.haline.desafio_icasei.data.dataclass.Thumbnail
import br.com.haline.desafio_icasei.data.dataclass.Thumbnails
import br.com.haline.desafio_icasei.data.dataclass.Video
import br.com.haline.desafio_icasei.data.dataclass.VideoId
import br.com.haline.desafio_icasei.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.data.local.entities.PlaylistEntity
import br.com.haline.desafio_icasei.data.local.entities.PlaylistVideoEntity
import br.com.haline.desafio_icasei.domain.model.FavoriteVideo
import br.com.haline.desafio_icasei.domain.repository.LocalRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.launch
import java.util.UUID

class LocalYouTubeViewModel(
    application: Application,
    private val repository: LocalRepository
) : AndroidViewModel(application) {

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
            val favoriteEntities = repository.getFavoriteVideos()

            val videos = favoriteEntities.map { entity ->
                getVideoById(entity.videoId)
            }
            _favoriteVideos.value = videos
        }
    }

    private fun loadPlaylists() {
        viewModelScope.launch {
            repository.getPlaylists().collect { playlistEntities ->
                val playlistsMapped = playlistEntities.map { entity ->
                    Playlist(
                        playlistId = entity.playlistId,
                        name = entity.name,
                        videos = repository.getVideosForPlaylist(entity.playlistId) // Para carregar vídeos da playlist
                    )
                }
                _playlists.value = playlistsMapped
            }
        }
    }


    private fun getVideoById(videoId: String): Video {
        return Video(
            id = VideoId(videoId),
            snippet = Snippet(
                title = "Video title",
                description = "Video description",
                thumbnails = Thumbnails(default = Thumbnail("url_to_thumbnail"))
            )
        )
    }

    fun addFavorite(video: FavoriteVideo) {
        viewModelScope.launch {
            val isAlreadyFavorite = repository.isFavorite(video.videoId).first()
            if (!isAlreadyFavorite) {
                repository.addFavoriteVideo(
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
            repository.removeFavoriteVideo(video.videoId)
            loadFavoriteVideos() // Atualiza a lista de favoritos
        }
    }

    fun isFavorite(videoId: String): Flow<Boolean> {
        return repository.isFavorite(videoId)
    }


    fun addVideoToPlaylist(playlistName: String, video: FavoriteVideo) {
        viewModelScope.launch {
            // Criação da playlist se não existir
            val playlistId = UUID.randomUUID().toString()
            repository.addPlaylist(
                PlaylistEntity(
                    playlistId = playlistId,
                    name = playlistName
                )
            )

            // Garantir que o vídeo está salvo como favorito
            repository.addFavoriteVideo(
                FavoriteVideoEntity(
                    videoId = video.videoId,
                    title = video.title,
                    description = video.description,
                    thumbnailUrl = video.thumbnailUrl
                )
            )

            // Vincular o vídeo à playlist
            repository.addVideoToPlaylist(
                PlaylistVideoEntity(
                    playlistId = playlistId,
                    videoId = video.videoId,
                    title = video.title,
                    description = video.description,
                    thumbnailUrl = video.thumbnailUrl
                )
            )
        }
    }


    fun getVideosForPlaylist(playlistId: String): StateFlow<List<FavoritesList>> {
        val videosFlow = MutableStateFlow<List<FavoritesList>>(emptyList())

        viewModelScope.launch {
            val videos = repository.getVideosForPlaylist(playlistId)
            videosFlow.value = videos
        }

        return videosFlow
    }
}
