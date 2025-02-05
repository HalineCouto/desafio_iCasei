package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.R
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.YouTubeViewModel
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.ui.components.PrimaryButtonColors
import org.koin.androidx.compose.koinViewModel

@Composable
fun YouTubeScreen(
    navController: NavController,
    viewModel: YouTubeViewModel = koinViewModel()
) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text(stringResource(R.string.search_search_video)) },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.searchVideos(query) },
            colors = PrimaryButtonColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(R.string.search))
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.videos.value) { video ->
                VideoItem(video = video, navController = navController)
            }
        }
    }
}

