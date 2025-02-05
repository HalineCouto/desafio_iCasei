package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.R
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LocalYouTubeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteVideosScreen(
    navController: NavController,
    viewModel: LocalYouTubeViewModel = koinViewModel()
) {
    val favoriteVideos by viewModel.favoriteVideos.collectAsState()

    if (favoriteVideos.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.favorite_not_videos),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    } else {
        LazyColumn {
            items(favoriteVideos) { video ->
                VideoItem(video = video, navController = navController)
            }
        }
    }
}
