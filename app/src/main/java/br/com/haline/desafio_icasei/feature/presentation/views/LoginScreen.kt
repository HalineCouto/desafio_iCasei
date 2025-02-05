package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.R
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_HOME
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val context = LocalContext.current
    var clicked by remember { mutableStateOf(false) }

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("738241025054-g5rjd69pn8jrv97uu0qmkc6o9la4f8ke.apps.googleusercontent.com")
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            if (idToken != null) {
                loginViewModel.signInWithGoogle(idToken) { isSuccess ->
                    if (isSuccess) {
                        Toast.makeText(context, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                        navController.navigate(ROUT_HOME)
                    } else {
                        Toast.makeText(context, "Falha no login", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: ApiException) {
            Toast.makeText(context, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Surface(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                clicked = true
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            },
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = "Google Button",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Login with Google")
                if (clicked) {
                    Spacer(modifier = Modifier.width(16.dp))
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
