package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.haline.desafio_icasei.MainActivity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.util.mockYouTubeResponse
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.FavoriteVideosScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.HomeScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.LoginScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.PLayListScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.PlaylistDetailScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.TermsOfUseScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.YouTubeScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.SplashScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.VideoList
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.VideoPlayerScreen

const val ROUT_SPLASH = "splash_rout"
const val ROUT_HOME = "home_rout"
const val ROUT_LOGIN = "login_rout"
const val ROUT_YOUTUBE_SEARCH = "youtube_search_rout"
const val ROUT_YOUTUBE_FAVORITES = "youtube_favorites_rout"
const val ROUT_YOUTUBE_PLAYLIST = "youtube_playlist_rout"
const val ROUT_YOUTUBE_TERMS = "youtube_terms_rout"
const val ROUT_VIDEO_PLAY = "video_play"
const val ROUT_VIDEO_LIST = "video_list"
const val ROUT_PLAYLIST_DETAILS = "youtube_playlist_details"

const val url = "https://www.youtube.com/t/terms"

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val activity = context as? MainActivity
    val videoId = activity?.intent?.getStringExtra("videoId")

    LaunchedEffect(videoId) {
        videoId?.let {
            navController.navigate(ROUT_VIDEO_PLAY) {
                popUpTo(ROUT_HOME) { inclusive = false }
                launchSingleTop = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = ROUT_SPLASH
    ) {
        composable(ROUT_SPLASH) {
            SplashScreen(navController = navController)
        }
        composable(ROUT_LOGIN) {
            LoginScreen(
                navController = navController
            )
        }
        composable(ROUT_HOME) {
            HomeScreen(navController = navController)
        }
        composable(ROUT_YOUTUBE_SEARCH) {
            YouTubeScreen(navController = navController)
        }
        composable(ROUT_VIDEO_PLAY) { _ ->
            val video = navController.previousBackStackEntry?.savedStateHandle?.get<Video>("video")
            video?.let {
                VideoPlayerScreen(
                    navController = navController,
                    video = it
                )
            } ?: run {
                VideoPlayerScreen(
                    navController = navController,
                    video = mockYouTubeResponse
                )
            }
        }

        composable(ROUT_YOUTUBE_FAVORITES) {
            FavoriteVideosScreen(navController = navController)
        }
        composable(ROUT_YOUTUBE_PLAYLIST) {
            PLayListScreen(navController = navController)
        }
        composable(ROUT_YOUTUBE_TERMS) {
            TermsOfUseScreen(
                navController = navController,
                urlTerms = url
            )
        }
        composable("$ROUT_PLAYLIST_DETAILS/{playlistId}") { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getString("playlistId") ?: ""
            PlaylistDetailScreen(
                navController = navController,
                playlistId = playlistId
            )

        }

        composable(ROUT_VIDEO_LIST) {
            val video =
                navController.previousBackStackEntry?.savedStateHandle?.get<List<Video>>("video")
            video?.let {
                VideoList(
                    navController = navController,
                    videos = it
                )
            }
        }
    }
}
