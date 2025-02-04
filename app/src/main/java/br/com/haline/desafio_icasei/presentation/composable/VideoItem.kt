package br.com.haline.desafio_icasei.presentation.composable


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.data.dataclass.Video
import coil.compose.AsyncImage

@Composable
fun VideoItem(
    video: Video,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("video_player/${video.id.videoId}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = video.snippet.thumbnails.default.url,
                contentDescription = video.snippet.title,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = video.snippet.title, fontWeight = FontWeight.Bold)
            Text(text = video.snippet.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}
