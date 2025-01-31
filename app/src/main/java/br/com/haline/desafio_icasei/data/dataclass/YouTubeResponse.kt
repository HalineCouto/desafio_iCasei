package br.com.haline.desafio_icasei.data.dataclass


data class YouTubeResponse(
    val items: List<Video>
)

data class Video(
    val snippet: Snippet,
    val id: VideoId
)

data class Snippet(
    val title: String,
    val description: String,
    val thumbnails: Thumbnails
)

data class VideoId(
    val videoId: String
)

data class Thumbnail(
    val url: String
)


data class Thumbnails(
    val default: Thumbnail
)

