package br.com.haline.desafio_icasei.data.dataclass

//A classe principal que contém uma lista de items, cada item sendo um vídeo.
data class YouTubeResponse(
    val items: List<Video>
)
//Contém o título, descrição e as miniaturas do vídeo.
data class Video(
    val snippet: Snippet,
    val id: VideoId
)
//Armazena o ID único do vídeo no YouTube.
data class Snippet(
    val title: String,
    val description: String,
    val thumbnails: Thumbnails
)
//Representa a URL da miniatura do vídeo.
data class VideoId(
    val videoId: String
)
//Uma classe que contém a miniatura em diferentes resoluções
data class Thumbnail(
    val url: String
)

//(aqui só consideramos a default para simplificação).
data class Thumbnails(
    val default: Thumbnail
)

