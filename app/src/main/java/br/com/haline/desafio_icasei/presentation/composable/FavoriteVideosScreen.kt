package br.com.haline.desafio_icasei.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.domain.model.FavoriteVideo
import br.com.haline.desafio_icasei.presentation.viewmodel.LocalYouTubeViewModel
import br.com.haline.desafio_icasei.presentation.viewmodel.YouTubeViewModel

@Composable
fun FavoriteVideosScreen(
    navController: NavController, viewModel: LocalYouTubeViewModel
) {
    val favoriteVideos by viewModel.favoriteVideos.collectAsState()

    LazyColumn {
        items(favoriteVideos) { video ->  // Aqui você já tem o objeto Video completo
            VideoItem(video = video, navController = navController)
        }
    }
}