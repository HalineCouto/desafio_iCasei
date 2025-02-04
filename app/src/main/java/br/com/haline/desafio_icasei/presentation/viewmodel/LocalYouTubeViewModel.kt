package br.com.haline.desafio_icasei.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.haline.desafio_icasei.data.dataclass.Snippet
import br.com.haline.desafio_icasei.data.dataclass.Thumbnail
import br.com.haline.desafio_icasei.data.dataclass.Thumbnails
import br.com.haline.desafio_icasei.data.dataclass.Video
import br.com.haline.desafio_icasei.data.dataclass.VideoId
import br.com.haline.desafio_icasei.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.domain.model.FavoriteVideo
import br.com.haline.desafio_icasei.domain.repository.LocalRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class LocalYouTubeViewModel(
    application: Application,
    private val repository: LocalRepository
) : AndroidViewModel(application) {

    private val _favoriteVideos = MutableStateFlow<List<Video>>(emptyList())
    val favoriteVideos: StateFlow<List<Video>> = _favoriteVideos


    init {
        loadFavoriteVideos()
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
            repository.addFavoriteVideo(
                FavoriteVideoEntity(
                    videoId = video.videoId,
                    title = video.title,
                    thumbnailUrl = video.thumbnailUrl,
                    description = video.description
                )
            )
            loadFavoriteVideos() // Atualiza a lista de favoritos
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
}
