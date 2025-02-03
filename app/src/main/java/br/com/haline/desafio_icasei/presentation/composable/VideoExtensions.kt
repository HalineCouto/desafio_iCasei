package br.com.haline.desafio_icasei.presentation.composable

import br.com.haline.desafio_icasei.data.dataclass.Video
import br.com.haline.desafio_icasei.domain.model.FavoriteVideo

// Função de Extensão: Video -> FavoriteVideo
fun Video.toFavoriteVideo(): FavoriteVideo {
    return FavoriteVideo(
        videoId = this.id.videoId,
        title = this.snippet.title,
        description = this.snippet.description,
        thumbnailUrl = this.snippet.thumbnails.default.url
    )
}