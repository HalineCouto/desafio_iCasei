package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository

class AddPlaylistUseCase(private val repository: LocalRepository) {
    suspend operator fun invoke(playlist: PlaylistEntity) {
        repository.addPlaylist(playlist)
    }
}