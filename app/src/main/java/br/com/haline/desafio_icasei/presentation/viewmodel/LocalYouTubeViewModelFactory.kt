package br.com.haline.desafio_icasei.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.haline.desafio_icasei.domain.repository.LocalRepository

class LocalYouTubeViewModelFactory(
    private val application: Application,
    private val repository: LocalRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocalYouTubeViewModel(application, repository) as T
    }
}
