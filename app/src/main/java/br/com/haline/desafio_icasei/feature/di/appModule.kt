package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import androidx.room.Room
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.remote.ServiceInstance
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.database.AppDatabase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.remote.YouTubeDataSource
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.remote.YouTubeDataSourceImpl
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.LocalRepository
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.YouTubeRepository
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository.YouTubeRepositoryImpl
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.AddFavoriteVideoUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.AddPlaylistUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.AddVideoToPlaylistUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.GetFavoriteVideosUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.GetPlaylistsUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.GetVideosForPlaylistUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.IsFavoriteVideoUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.RemoveFavoriteVideoUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.RemoveVideoFromPlaylistUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.usecase.SearchVideosUseCase
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LocalYouTubeViewModel
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LoginViewModel
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.YouTubeViewModel
import com.google.firebase.auth.FirebaseAuth

private val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().videoDao() }
    single { get<AppDatabase>().playlistVideoDao() }
    single { FirebaseAuth.getInstance() }


    single {
        LocalRepository(
            playlistVideoDao = get(),
            videoDao = get()
        )
    }

    single { ServiceInstance.youtubeApi }
    single<YouTubeDataSource> { YouTubeDataSourceImpl(api = get()) }
    single<YouTubeRepository> { YouTubeRepositoryImpl(dataSource = get()) }

}

val domainModule = module {
    factory { SearchVideosUseCase(get()) }
    factory { AddFavoriteVideoUseCase(get()) }
    factory { GetFavoriteVideosUseCase(get()) }
    factory { AddVideoToPlaylistUseCase(get()) }
    factory { GetPlaylistsUseCase(get()) }
    factory { RemoveFavoriteVideoUseCase(get()) }
    factory { IsFavoriteVideoUseCase(get()) }
    factory { AddPlaylistUseCase(get()) }
    factory { GetVideosForPlaylistUseCase(get()) }
    factory { RemoveVideoFromPlaylistUseCase(get()) }
}

private val presentationModule = module {
    viewModel { LoginViewModel() }
    viewModel {
        LocalYouTubeViewModel(
            getFavoritesUseCase = get(),
            getPlaylistsUseCase = get(),
            addFavoriteUseCase = get(),
            addVideoToPlaylistUseCase = get(),
            isFavoriteVideoUseCase = get(),
            addPlaylistUseCase = get(),
            removeFavoriteVideoUseCase = get(),
            getVideosForPlaylistUseCase = get(),
            removeVideoFromPlaylistUseCase = get()
        )
    }
    viewModel {
        YouTubeViewModel(
            searchVideosUseCase = get()
        )
    }

}


fun getAppModule() = listOf(dataModule, presentationModule, domainModule)