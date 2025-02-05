package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository

class GetVideosForPlaylistUseCase(private val repository: LocalRepository) {
    suspend operator fun invoke(playlistId: String): List<FavoriteVideoEntity> {
        return repository.getVideosForPlaylist(playlistId)
    }
}