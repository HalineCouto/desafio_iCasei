package br.com.haline.desafio_icasei

import android.app.Application
import androidx.room.Room
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.util.createNotificationChannel
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.database.AppDatabase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.di.getAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val appDatabase = "app_database"

class MainApplication : Application() {
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel(this)

        startKoin {
            androidContext(this@MainApplication)
            modules(getAppModule())
        }

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            appDatabase
        ).fallbackToDestructiveMigration()
            .build()

    }
}
