package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_VIDEO_PLAY

@Composable
fun VideoList(
    videos: List<Video>,
    navController: NavController
) {

    var showPlaylistDialog by remember { mutableStateOf(false) }
    var selectedVideoId by remember { mutableStateOf("") }

    LazyColumn {
        items(videos) { video ->
            val videoId = video.id.videoId
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = video.snippet.title, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("video", video)
                    navController.navigate(ROUT_VIDEO_PLAY)
                }) {
                    Text("Assistir Vídeo")
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    selectedVideoId = videoId
                    showPlaylistDialog = true
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar à Playlist")
                }
            }
        }
    }
}
