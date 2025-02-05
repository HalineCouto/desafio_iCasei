package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository

class RemoveVideoFromPlaylistUseCase(private val repository: LocalRepository) {
    suspend operator fun invoke(playlistId: String, videoId: String) {
        repository.removeVideoFromPlaylist(playlistId, videoId)
    }
}