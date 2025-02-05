package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class YouTubeResponse(
    val items: List<Video>
) : Parcelable

@Parcelize
data class Video(
    val snippet: Snippet,
    val id: VideoId
) : Parcelable

@Parcelize
data class Snippet(
    val title: String,
    val description: String,
    val thumbnails: Thumbnails
) : Parcelable

@Parcelize
data class VideoId(
    val videoId: String
) : Parcelable

@Parcelize
data class Thumbnail(
    val url: String
) : Parcelable

@Parcelize
data class Thumbnails(
    val default: Thumbnail
) : Parcelable

