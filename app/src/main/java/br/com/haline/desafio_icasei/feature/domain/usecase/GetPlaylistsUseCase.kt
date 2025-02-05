package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetPlaylistsUseCase(private val repository: LocalRepository) {
    operator fun invoke(): Flow<List<PlaylistEntity>> {
        return repository.getPlaylists()
    }
}
