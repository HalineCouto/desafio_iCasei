package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
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
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Playlist
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.model.FavoriteVideo
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LocalYouTubeViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayerScreen(
    navController: NavController,
    video: Video,
    viewModel: LocalYouTubeViewModel = koinViewModel()
) {

    val isFavorite by viewModel.isFavorite(video.id.videoId).collectAsState(initial = false)
    var showPlaylistDialog by remember { mutableStateOf(false) }


    // Coletando as playlists
    val playlists by viewModel.playlists.collectAsState()

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
                                    video.id.videoId,
                                    video.snippet.title,
                                    video.snippet.description,
                                    video.snippet.thumbnails.default.url
                                )
                            )
                        } else {
                            viewModel.addFavorite(
                                FavoriteVideo(
                                    video.id.videoId,
                                    video.snippet.title,
                                    video.snippet.description,
                                    video.snippet.thumbnails.default.url
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
                            youTubePlayer.loadVideo(video.id.videoId, 0f)
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
            video.id.videoId,
            video.snippet.title,
            video.snippet.description,
            video.snippet.thumbnails.default.url
        )

        CreatePlaylistDialog(
            playlists = playListsName(playlists),
            onDismiss = { showPlaylistDialog = false },
            onConfirm = { playlistName ->
                viewModel.addVideoToPlaylist(playlistName, currentVideo)
            }
        )
    }
}

private fun playListsName(playlists: List<Playlist>): List<String> {
    return playlists.map { it.name }

}

