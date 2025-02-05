package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
fun PLayListScreen(
    navController: NavController,
    viewModel: LocalYouTubeViewModel = koinViewModel()
) {

    val playlists by viewModel.playlists.collectAsState(initial = emptyList())

    LazyColumn {
        items(playlists) { playlist ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = playlist.name, style = MaterialTheme.typography.titleMedium)

                Button(onClick = {
                    navController.navigate("playlist_detail/${playlist.playlistId}")
                }) {
                    Text("Ver Vídeos")
                }
            }
        }
    }
}