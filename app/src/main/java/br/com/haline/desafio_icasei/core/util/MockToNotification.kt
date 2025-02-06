package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.util

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Snippet
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Thumbnail
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Thumbnails
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.VideoId

val mockYouTubeResponse =
    Video(
        snippet = Snippet(
            title = "Vídeo de Teste",
            description = "Descrição do vídeo de teste.",
            thumbnails = Thumbnails(
                default = Thumbnail(
                    url = "https://img.youtube.com/vi/dQw4w9WgXcQ/default.jpg"
                )
            )
        ),
        id = VideoId(videoId = "dQw4w9WgXcQ")
    )


