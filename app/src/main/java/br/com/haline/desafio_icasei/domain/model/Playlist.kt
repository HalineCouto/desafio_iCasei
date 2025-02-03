package br.com.haline.desafio_icasei.domain.model

import br.com.haline.desafio_icasei.data.dataclass.Video
import java.util.UUID

data class Playlist(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val videos: List<Video> = emptyList()
)
