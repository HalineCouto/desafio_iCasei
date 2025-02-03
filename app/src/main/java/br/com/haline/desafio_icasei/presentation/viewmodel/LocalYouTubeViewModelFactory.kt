package br.com.haline.desafio_icasei.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.haline.desafio_icasei.domain.repository.FavoriteRepository

class LocalYouTubeViewModelFactory(private val repository: FavoriteRepository) : ViewModelProvider.NewInstanceFactory()  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocalYouTubeViewModel(Application(), repository)  as T
    }
}