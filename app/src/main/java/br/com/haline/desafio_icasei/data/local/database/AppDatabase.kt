package br.com.haline.desafio_icasei.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.haline.desafio_icasei.data.local.dao.VideoDao
import br.com.haline.desafio_icasei.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.data.local.entities.PlaylistEntity

@Database(entities = [FavoriteVideoEntity::class, PlaylistEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}