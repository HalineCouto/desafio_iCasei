package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.SearchVideosUseCase

import kotlinx.coroutines.launch

class YouTubeViewModel(
    private val searchVideosUseCase: SearchVideosUseCase
) : ViewModel() {

    private val _videos = mutableStateOf<List<Video>>(emptyList())
    val videos: State<List<Video>> = _videos

    fun searchVideos(query: String) {
        viewModelScope.launch {
            val apiKey = "AIzaSyAWBCpHBk8SUMz7MW2nadKWyE2X2Z68NH8"
            _videos.value = searchVideosUseCase(query, apiKey)
        }
    }
}
