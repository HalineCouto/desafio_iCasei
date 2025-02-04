package br.com.haline.desafio_icasei.presentation.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.presentation.viewmodel.LocalYouTubeViewModel

@Composable
fun FavoriteVideosScreen(
    navController: NavController,
    viewModel: LocalYouTubeViewModel
) {
    val favoriteVideos by viewModel.favoriteVideos.collectAsState()

    LazyColumn {
        items(favoriteVideos) { video ->
            VideoItem(video = video, navController = navController)
        }
    }
}