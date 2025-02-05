package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository

class RemoveFavoriteVideoUseCase(private val repository: LocalRepository) {
    suspend operator fun invoke(videoId: String) {
        repository.removeFavoriteVideo(videoId)
    }
}