package br.com.haline.desafio_icasei

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.util.createNotificationChannel
import br.com.haline.desafio_icasei.data.dataclass.Snippet
import br.com.haline.desafio_icasei.data.dataclass.Thumbnail
import br.com.haline.desafio_icasei.data.dataclass.Thumbnails
import br.com.haline.desafio_icasei.data.dataclass.Video
import br.com.haline.desafio_icasei.data.dataclass.VideoId
import br.com.haline.desafio_icasei.domain.repository.FavoriteRepository
import br.com.haline.desafio_icasei.domain.repository.LocalRepository
import br.com.haline.desafio_icasei.presentation.composable.AddFavoriteButton
import br.com.haline.desafio_icasei.presentation.composable.FavoriteVideosScreen
import br.com.haline.desafio_icasei.presentation.composable.HomeScreen
import br.com.haline.desafio_icasei.presentation.composable.LoginScreen
import br.com.haline.desafio_icasei.presentation.composable.TermsOfUseScreen
import br.com.haline.desafio_icasei.presentation.composable.VideoList
import br.com.haline.desafio_icasei.presentation.composable.VideoPlayerScreen
import br.com.haline.desafio_icasei.presentation.composable.YouTubeScreen
import br.com.haline.desafio_icasei.presentation.viewmodel.LocalYouTubeViewModel
import br.com.haline.desafio_icasei.presentation.viewmodel.LocalYouTubeViewModelFactory
import br.com.haline.desafio_icasei.ui.theme.Desafio_iCaseiTheme
import br.com.haline.desafio_icasei.presentation.viewmodel.LoginViewModel
import br.com.haline.desafio_icasei.presentation.viewmodel.YouTubeViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GetGoogleIdOption.Builder
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel(this)


        val gso = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId("738241025054-g5rjd69pn8jrv97uu0qmkc6o9la4f8ke.apps.googleusercontent.com")

        googleSignInClient = gso

        enableEdgeToEdge()
        setContent {
            Desafio_iCaseiTheme {
                val navController = rememberNavController()
                val scope: CoroutineScope = rememberCoroutineScope()
                val context = LocalContext.current
                val credentialManager: CredentialManager = CredentialManager.create(context)

                val videoIdFromIntent = intent?.getStringExtra("videoId")
                if (!videoIdFromIntent.isNullOrEmpty()) {
                    LaunchedEffect(videoIdFromIntent) {
                        navController.navigate("video_player/$videoIdFromIntent")
                    }
                }

                NavHost(navController = navController, startDestination = "login_screen") {
                    composable("login_screen") {
                        LoginScreen(
                            onClicked = {
                                val signInIntent = googleSignInClient.build()

                                val request = GetCredentialRequest.Builder()
                                    .addCredentialOption(signInIntent)
                                    .build()

                                scope.launch {
                                    try {
                                        val result = credentialManager.getCredential(
                                            context = context,
                                            request = request
                                        )
                                        val credential = result.credential
                                        val googleIdTokenCredencial = GoogleIdTokenCredential
                                            .createFrom(credential.data)

                                        loginViewModel.signInWithGoogle(token = googleIdTokenCredencial.idToken) { success ->
                                            if (success) {
                                                navController.popBackStack()
                                                navController.navigate("home_screen")
                                            }
                                        }
                                    } catch (e: GetCredentialCancellationException) {
                                        Toast.makeText(
                                            context,
                                            "Erro ao autenticar: ${e.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        e.printStackTrace()
                                    }
                                }
                            }
                        )
                    }

                    composable("home_screen") {
                        HomeScreen(navController = navController)
                    }

                    composable("youtube_screen") {
                        YouTubeScreen(navController = navController)
                    }

                    composable("terms_of_use") {
                        TermsOfUseScreen(navController)
                    }

                    composable("favorite_video") {
                        val app = LocalContext.current.applicationContext as App
                        // Crie o repositório utilizando o VideoDao do seu AppDatabase
                        val repository = LocalRepository(app.database.videoDao())
                        // Crie a factory passando o Application e o repositório
                        val viewModelFactory = LocalYouTubeViewModelFactory(app, repository)
                        // Obtenha o ViewModel utilizando a factory personalizada
                        val localYouTubeViewModel: LocalYouTubeViewModel =
                            viewModel(factory = viewModelFactory)

                        FavoriteVideosScreen(navController, localYouTubeViewModel)
                    }

                    composable("video_player/{videoId}") { backStackEntry ->
                        val videoId = backStackEntry.arguments?.getString("videoId") ?: ""

                        val app = application as App
                        val repository = LocalRepository(app.database.videoDao())

                        val viewModelFactory = LocalYouTubeViewModelFactory(
                            repository = repository,
                            application = app
                        )
                        val localYouTubeViewModel: LocalYouTubeViewModel =
                            viewModel(factory = viewModelFactory)

                        VideoPlayerScreen(navController, videoId, localYouTubeViewModel)
                    }

                    composable("list_video") {
                        val videos = listOf(
                            // Adicione vídeos mockados ou provenientes do ViewModel
                            Video(
                                id = VideoId("123"),
                                snippet = Snippet(
                                    title = "Título do vídeo",
                                    description = "Descrição do vídeo",
                                    thumbnails = Thumbnails(default = Thumbnail("url_to_thumbnail"))
                                )
                            )
                        )
                        VideoList(videos = videos, navController = navController)
                    }

                    composable("add_favorite_button") {
                        val video = Video(
                            id = VideoId("123"),
                            snippet = Snippet(
                                title = "Título do vídeo",
                                description = "Descrição do vídeo",
                                thumbnails = Thumbnails(default = Thumbnail("url_to_thumbnail"))
                            )
                        )

                        val localYouTubeViewModel = viewModel<LocalYouTubeViewModel>()

                        AddFavoriteButton(video, localYouTubeViewModel)
                    }
                }
            }
        }
    }
}

