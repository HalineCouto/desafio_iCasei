package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class IsFavoriteVideoUseCase(private val repository: LocalRepository) {
    operator fun invoke(videoId: String): Flow<Boolean> {
        return repository.isFavorite(videoId)
    }
}