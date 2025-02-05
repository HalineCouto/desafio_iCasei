package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views


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
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_VIDEO_PLAY
import coil.compose.AsyncImage

@Composable
fun VideoItem( // tela que exibe o video
    video: Video,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set("video", video)
                navController.navigate(ROUT_VIDEO_PLAY)
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
