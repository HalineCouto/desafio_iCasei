package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_VIDEO_PLAY
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LocalYouTubeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaylistDetailScreen(
    navController: NavController,
    playlistId: String,
    viewModel: LocalYouTubeViewModel = koinViewModel()

) {
    val videos by viewModel.getVideosForPlaylist(playlistId).collectAsState()
    val showDialog = remember { mutableStateOf(false) }
    val videoToRemove = remember { mutableStateOf<String?>(null) }

    if (videos.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Nenhum vídeo disponível.")
        }
    } else {
        LazyColumn {
            items(videos) { video ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = video.snippet.title,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                navController.navigate("$ROUT_VIDEO_PLAY/${video}")
                            }
                    )
                    Button(onClick = {
                        videoToRemove.value = video.id.videoId
                        showDialog.value = true
                    }) {
                        Text("Remover")
                    }
                }
            }
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Confirmar Remoção") },
            text = { Text("Tem certeza que deseja remover este vídeo da playlist?") },
            confirmButton = {
                TextButton(onClick = {
                    videoToRemove.value?.let { videoId ->
                        viewModel.removeVideoFromPlaylist(playlistId, videoId)
                    }
                    showDialog.value = false
                }) {
                    Text("Sim")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog.value = false
                }) {
                    Text("Cancelar")
                }
            }
        )
    }
}