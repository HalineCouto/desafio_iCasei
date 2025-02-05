package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.dao.PlaylistVideoDao
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.dao.VideoDao
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistVideoEntity


@Database(
    entities = [
        FavoriteVideoEntity::class,
        PlaylistEntity::class,
        PlaylistVideoEntity::class
    ],
    version = 2
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
    abstract fun playlistVideoDao(): PlaylistVideoDao

}