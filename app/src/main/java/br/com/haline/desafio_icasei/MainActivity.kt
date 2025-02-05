package br.com.haline.desafio_icasei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.AppNavigation
import br.com.haline.desafio_icasei.ui.theme.Desafio_iCaseiTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyApp)
        super.onCreate(savedInstanceState)
        setContent {
            Desafio_iCaseiTheme {
                AppNavigation()
            }
        }
    }
}
