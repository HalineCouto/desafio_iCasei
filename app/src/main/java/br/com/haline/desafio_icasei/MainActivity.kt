package br.com.haline.desafio_icasei

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.haline.desafio_icasei.presentation.composable.LoginScreen
import br.com.haline.desafio_icasei.presentation.composable.TermsOfUseScreen
import br.com.haline.desafio_icasei.presentation.composable.VideoPlayerScreen
import br.com.haline.desafio_icasei.presentation.composable.YouTubeScreen
import br.com.haline.desafio_icasei.ui.theme.Desafio_iCaseiTheme
import br.com.haline.desafio_icasei.presentation.viewmodel.LoginViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GetGoogleIdOption.Builder
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                                                navController.navigate("youtube_screen")
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

                    composable("youtube_screen") {
                        YouTubeScreen(navController = navController)                    }

                    composable("terms_of_use") {
                        TermsOfUseScreen(navController)
                    }

                    composable("video_player/{videoId}") { backStackEntry ->
                        val videoId = backStackEntry.arguments?.getString("videoId") ?: ""
                        VideoPlayerScreen(navController, videoId)
                    }

                }
            }
        }
    }
}

