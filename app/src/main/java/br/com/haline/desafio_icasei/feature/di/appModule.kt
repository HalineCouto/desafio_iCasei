package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import androidx.room.Room
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.database.AppDatabase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LocalYouTubeViewModel
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LoginViewModel
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.YouTubeViewModel

private val dataModule = module {

    // Database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    // DAOs
    single { get<AppDatabase>().videoDao() }
    single { get<AppDatabase>().playlistVideoDao() }

    // Repository
    single { LocalRepository(get(), get()) }

}
private val useCaseModule = module {


}
private val presentationModule = module {
    // ViewModels
    viewModel { LoginViewModel() }
    viewModel { LocalYouTubeViewModel(get(), get()) }
    viewModel { YouTubeViewModel() }
}


fun getAppModule() = listOf(dataModule, presentationModule)