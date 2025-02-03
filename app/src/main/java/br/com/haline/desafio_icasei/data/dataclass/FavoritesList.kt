package br.com.haline.desafio_icasei.data.dataclass

data class FavoritesList(
    val videoId: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String
)
data class Playlist(
    val playlistId: String,
    val name: String,
    val videos: List<FavoritesList>
)