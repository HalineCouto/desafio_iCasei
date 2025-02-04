package br.com.haline.desafio_icasei.presentation.composable

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import br.com.haline.desafio_icasei.data.dataclass.Video
import br.com.haline.desafio_icasei.domain.model.FavoriteVideo
import br.com.haline.desafio_icasei.presentation.viewmodel.LocalYouTubeViewModel

@Composable
fun AddFavoriteButton(
    video: Video,
    localYouTubeViewModel: LocalYouTubeViewModel
) {
    Button(onClick = {
        val favoriteVideo = FavoriteVideo(
            videoId = video.id.videoId,
            title = video.snippet.title,
            description = video.snippet.description,
            thumbnailUrl = video.snippet.thumbnails.default.url
        )

        localYouTubeViewModel.addFavorite(favoriteVideo)
    }) {
        Text("Adicionar aos Favoritos")
    }
}