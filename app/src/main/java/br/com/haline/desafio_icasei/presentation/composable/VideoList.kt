package br.com.haline.desafio_icasei.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.data.dataclass.Video


@Composable
fun VideoList(videos: List<Video>, navController: NavController) {
    LazyColumn {
        items(videos) { video ->
            val videoId = video.id.videoId
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = video.snippet.title, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    // Navega para a tela do vídeo
                    navController.navigate("video_player/$videoId")
                }) {
                    Text("Assistir Vídeo")
                }
            }
        }
    }
}

