package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LocalYouTubeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaylistDetailScreen(
    navController: NavController,
    playlistId: String,
    viewModel: LocalYouTubeViewModel = koinViewModel()

) {
    val videos by viewModel.getVideosForPlaylist(playlistId).collectAsState()

    LazyColumn {
        items(videos) { video ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // Ao selecionar um vídeo, navega para a tela de reprodução
                        navController.navigate("video_player/${video.videoId}")
                    }
                    .padding(16.dp)
            ) {
                Text(text = video.title)
            }
        }
    }
}