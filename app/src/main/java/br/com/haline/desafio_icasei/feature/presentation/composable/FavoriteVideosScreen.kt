package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LocalYouTubeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteVideosScreen(
    navController: NavController,
    viewModel: LocalYouTubeViewModel = koinViewModel()
) {
    val favoriteVideos by viewModel.favoriteVideos.collectAsState()

    LazyColumn {
        items(favoriteVideos) { video ->
            VideoItem(video = video, navController = navController)
        }
    }
}