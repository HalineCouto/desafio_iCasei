package br.com.haline.desafio_icasei

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.AppNavigation
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.ui.theme.Desafio_iCaseiTheme
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Desafio_iCaseiTheme {
                AppNavigation()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}
