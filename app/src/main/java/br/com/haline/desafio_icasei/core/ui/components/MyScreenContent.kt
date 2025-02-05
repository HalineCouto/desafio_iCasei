package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.res.colorResource
import br.com.haline.desafio_icasei.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MyScreenContent() {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = colorResource(R.color.primary_icasei)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = false
        )
    }
}