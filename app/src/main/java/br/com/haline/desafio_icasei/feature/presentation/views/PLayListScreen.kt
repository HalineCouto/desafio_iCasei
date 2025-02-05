package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_PLAYLIST_DETAILS
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_VIDEO_PLAY
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_YOUTUBE_PLAYLIST
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LocalYouTubeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PLayListScreen(
    navController: NavController,
    viewModel: LocalYouTubeViewModel = koinViewModel()
) {

    val playlists by viewModel.playlists.collectAsState(initial = emptyList())

    LazyColumn {
        items(playlists) { playlist ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("${ROUT_PLAYLIST_DETAILS}/${playlist.playlistId}")
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = playlist.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Ir para a lista de reprodução",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}


