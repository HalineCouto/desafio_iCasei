package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistVideoEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository

class AddVideoToPlaylistUseCase(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(playlistId: String, videoId: String, title: String, description: String, thumbnailUrl: String) {
        val playlistVideo = PlaylistVideoEntity(
            playlistId = playlistId,
            videoId = videoId,
            title = title,
            description = description,
            thumbnailUrl = thumbnailUrl
        )
        repository.addVideoToPlaylist(playlistVideo)
    }
}