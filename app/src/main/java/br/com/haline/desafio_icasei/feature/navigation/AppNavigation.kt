package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.composable.FavoriteVideosScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.composable.HomeScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.composable.PLayListScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.composable.TermsOfUseScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.composable.YouTubeScreen
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views.SplashScreen

const val ROUT_SPLASH = "splash_rout"
const val ROUT_HOME = "home_rout"
const val ROUT_YOUTUBE_SEARCH = "youtube_search_rout"
const val ROUT_YOUTUBE_FAVORITES = "youtube_favorites_rout"
const val ROUT_YOUTUBE_PLAYLIST = "youtube_playlist_rout"
const val ROUT_YOUTUBE_TERMS = "youtube_terms_rout"
const val ROUT_YOUTUBE_OPEN_NOTIFICATION = "youtube_open_notification_rout"

const val url = "https://www.youtube.com/t/terms"

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ROUT_SPLASH
    ) {
        composable(ROUT_SPLASH) {
            SplashScreen(navController = navController)
        }
        composable(ROUT_HOME) {
            HomeScreen(navController = navController)
        }
        composable(ROUT_YOUTUBE_SEARCH) {
            YouTubeScreen(navController = navController)
        }
        composable(ROUT_YOUTUBE_FAVORITES) {
            FavoriteVideosScreen(navController = navController)
        }
        composable(ROUT_YOUTUBE_PLAYLIST) {
            PLayListScreen(
                navController = navController)
        }
        composable(ROUT_YOUTUBE_TERMS) {
            TermsOfUseScreen(
                navController = navController,
                urlTerms = url

            )
        }

        composable(ROUT_YOUTUBE_OPEN_NOTIFICATION) {
            HomeScreen(navController = navController)
        }
    }
}

