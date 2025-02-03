package br.com.haline.desafio_icasei.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.haline.desafio_icasei.data.dataclass.Snippet
import br.com.haline.desafio_icasei.data.dataclass.Thumbnail
import br.com.haline.desafio_icasei.data.dataclass.Thumbnails
import br.com.haline.desafio_icasei.data.dataclass.Video
import br.com.haline.desafio_icasei.data.dataclass.VideoId
import br.com.haline.desafio_icasei.domain.model.FavoriteVideo
import br.com.haline.desafio_icasei.domain.model.Playlist
import br.com.haline.desafio_icasei.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.launch

class LocalYouTubeViewModel(application: Application, private val repository: FavoriteRepository) :
    AndroidViewModel(application) {


    private val _favoriteVideos =
        MutableStateFlow<List<Video>>(emptyList())  // Agora vai retornar uma lista de objetos Video
    val favoriteVideos: StateFlow<List<Video>> = _favoriteVideos

    private val _playlists = mutableStateOf<List<Playlist>>(emptyList())
    val playlists: State<List<Playlist>> = _playlists


    init {
        loadFavoriteVideos()
    }

    private fun loadFavoriteVideos() {
        viewModelScope.launch {
            val favoriteVideoIds =
                repository.getFavoriteVideos()  // Obtém os IDs dos vídeos favoritos

            // Agora buscamos os vídeos completos com base nos IDs
            val videos = favoriteVideoIds.mapNotNull { videoId ->
                // Aqui você deve buscar o vídeo completo de acordo com o seu repositório ou API
                getVideoById(videoId)  // Vamos buscar o vídeo completo
            }
            _favoriteVideos.value = videos  // Atualiza a lista de vídeos favoritos
        }
    }

    private fun getVideoById(videoId: String): Video? {

        return Video(
            id = VideoId(videoId),  // Simulando a criação de um vídeo com o ID
            snippet = Snippet(
                title = "Video title",
                description = "Video description",
                thumbnails = Thumbnails(default = Thumbnail("url_to_thumbnail"))
            )
        )
    }


    fun addFavorite(video: FavoriteVideo) {
        repository.addFavorite(video.videoId)
        loadFavoriteVideos() // Atualiza a lista de favoritos
    }

    fun removeFavorite(video: FavoriteVideo) {
        repository.removeFavorite(video.videoId)
        loadFavoriteVideos() // Atualiza a lista de favoritos
    }

    fun isFavorite(videoId: String): Flow<Boolean> {
        return flow {
            emit(repository.isFavorite(videoId))  // Retorna Flow<Boolean>
        }
    }

//    fun isFavorite(videoId: String): StateFlow<Boolean> {
//        return _favoriteVideos.map { it.contains(videoId) }
//            .stateIn(viewModelScope, SharingStarted.Eagerly, false)
//    }
//
//    fun removeFavoriteVideo(videoId: String) {
//        _favoriteVideos.value = _favoriteVideos.value - videoId
//    }
//
//    fun addFavoriteVideo(video: FavoriteVideo) {
//        _favoriteVideos.value = _favoriteVideos.value + video.videoId
//    }
//
//    fun addPlaylist(playlist: Playlist) {
//        viewModelScope.launch {
//            localRepository.addPlaylist(
//                PlaylistEntity(
//                    playlistId = playlist.playlistId,
//                    name = playlist.name
//                )
//            )
//            loadPlaylists()
//        }
//    }
}