package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.YouTubeRepository

class SearchVideosUseCase(private val repository: YouTubeRepository) {
    suspend operator fun invoke(query: String, apiKey: String): List<Video> {
        return repository.searchVideos(query, apiKey)
    }
}