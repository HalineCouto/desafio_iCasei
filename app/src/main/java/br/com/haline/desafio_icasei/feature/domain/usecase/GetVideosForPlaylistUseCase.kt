package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.FavoritesList
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository

class GetVideosForPlaylistUseCase(private val repository: LocalRepository) {
    suspend operator fun invoke(playlistId: String): List<FavoritesList> {
        return repository.getVideosForPlaylist(playlistId)
    }
}