package br.com.haline.desafio_icasei.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.presentation.viewmodel.YouTubeViewModel

@Composable
fun YouTubeScreen(viewModel: YouTubeViewModel = viewModel(), navController: NavController) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de busca
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar vídeos no YouTbe") },
            modifier = Modifier.fillMaxWidth()
        )

        // Botão de pesquisa
        Button(onClick = { viewModel.searchVideos(query) }) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Exibição dos resultados
        LazyColumn {
            items(viewModel.videos.value) { video ->
                VideoItem(video = video, navController = navController)
            }
        }
    }
}

