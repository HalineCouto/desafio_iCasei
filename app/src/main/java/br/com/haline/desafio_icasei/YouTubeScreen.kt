package br.com.haline.desafio_icasei

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
import br.com.haline.desafio_icasei.viewmodel.YouTubeViewModel

@Composable
fun YouTubeScreen(viewModel: YouTubeViewModel = viewModel()) {
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
            label = { Text("Permitir a busca de vídeos no Youtube através de um termo digitado ") },
            modifier = Modifier.fillMaxWidth()
        )

        // Botão de pesquisa
        Button(onClick = { viewModel.searchVideos(query) }) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibição dos resultados
        LazyColumn {
            items(viewModel.videos.value) { video ->
                VideoItem(video = video)
            }
        }
    }
}

