package br.com.haline.desafio_icasei

import android.app.Application
import androidx.room.Room
import br.com.haline.desafio_icasei.data.local.database.AppDatabase

class App : Application() {
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"  // Nome do banco de dados
        ).build()
    }
}
