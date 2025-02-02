package br.com.haline.desafio_icasei.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.haline.desafio_icasei.data.dataclass.Video
import br.com.haline.desafio_icasei.data.repository.YouTubeRepository
import kotlinx.coroutines.launch

class YouTubeViewModel : ViewModel() {


    private val youTubeRepository = YouTubeRepository()

    private val _videos = mutableStateOf<List<Video>>(emptyList())
    val videos: State<List<Video>> = _videos

    fun searchVideos(query: String) {
        viewModelScope.launch {
            val apiKey = "AIzaSyAWBCpHBk8SUMz7MW2nadKWyE2X2Z68NH8"
            val result = youTubeRepository.searchVideos(query, apiKey)
            _videos.value = result
        }
    }
}
