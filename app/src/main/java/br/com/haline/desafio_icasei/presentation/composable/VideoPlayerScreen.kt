package br.com.haline.desafio_icasei.presentation.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.presentation.composable.CreatePlaylistDialog
import br.com.haline.desafio_icasei.domain.model.FavoriteVideo
import br.com.haline.desafio_icasei.presentation.viewmodel.LocalYouTubeViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayerScreen(
    navController: NavController,
    videoId: String,
    viewModel: LocalYouTubeViewModel
) {

    val isFavorite by viewModel.isFavorite(videoId).collectAsState(initial = false)
    var showPlaylistDialog by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Assistindo Vídeo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (isFavorite) {
                            viewModel.removeFavorite(
                                FavoriteVideo(
                                    videoId,
                                    "Título",
                                    "Descrição",
                                    "URL da miniatura"
                                )
                            )
                        } else {
                            viewModel.addFavorite(
                                FavoriteVideo(
                                    videoId,
                                    "Título",
                                    "Descrição",
                                    "URL da miniatura"
                                )
                            )
                        }
                    }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favoritar",
                            tint = if (isFavorite) Color.Red else Color.Gray
                        )
                    }
                    IconButton(onClick = { showPlaylistDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Adicionar à Playlist")
                    }
                }
            )
        }
    ) { padding ->
        AndroidView(
            factory = { context ->
                YouTubePlayerView(context).apply {
                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }

    if (showPlaylistDialog) {
        val currentVideo = FavoriteVideo(
            videoId = videoId,
            title = "Título do Vídeo",
            description = "Descrição do Vídeo",
            thumbnailUrl = "URL da miniatura"
        )

        CreatePlaylistDialog(
            onDismiss = { showPlaylistDialog = false },
            onConfirm = { playlistName ->
                viewModel.addVideoToPlaylist(playlistName, currentVideo)
            }
        )
    }

}