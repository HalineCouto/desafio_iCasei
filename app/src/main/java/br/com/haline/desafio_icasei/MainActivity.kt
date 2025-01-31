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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.haline.desafio_icasei.ui.theme.Desafio_iCaseiTheme
import br.com.haline.desafio_icasei.viewmodel.LoginViewModel
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GetGoogleIdOption.Builder
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                                val signInIntent = googleSignInClient
                                    .setFilterByAuthorizedAccounts(true)
                                    .setServerClientId("AIzaSyBgkstRDjT29FPyWK8XhjlIt1McUECbkVw")
                                    .build()

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
                                    } catch (e: ApiException) {
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
                        YouTubeScreen()
                    }
                }
            }
        }
    }
}

