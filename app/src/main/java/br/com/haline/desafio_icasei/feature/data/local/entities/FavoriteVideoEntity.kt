package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_videos")
data class FavoriteVideoEntity(
    @PrimaryKey
    val videoId: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String
)
