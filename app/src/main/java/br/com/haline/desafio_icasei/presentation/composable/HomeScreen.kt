package br.com.haline.desafio_icasei.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título ou algum cabeçalho
        Text(text = "Bem-vindo ao YouTube App", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(32.dp))

        // Botões de navegação
        Button(onClick = {
            navController.navigate("youtube_screen") // Navega para a tela de busca
        }) {
            Text("Buscar vídeos no YouTube")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("favorite_video") // Navega para a tela de vídeos favoritos
        }) {
            Text("Vídeos Favoritos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("video_player/{videoId}") // Navega para a tela de listas de reprodução
        }) {
            Text("Listas de Reprodução")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("terms_of_use") // Navega para o WebView dos termos de uso
        }) {
            Text("Termos de Uso do YouTube")
        }
    }
}