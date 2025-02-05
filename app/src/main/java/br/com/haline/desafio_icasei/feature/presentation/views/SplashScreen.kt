package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.R
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_HOME
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_SPLASH
import kotlinx.coroutines.delay
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.ui.components.MyScreenContent


const val delayTime = 3000L

@Composable
fun SplashScreen(navController: NavController) {

    MyScreenContent()

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        isVisible = true
        delay(delayTime)
        isVisible = false
        delay(700)
        navController.navigate(ROUT_HOME) {
            popUpTo(ROUT_SPLASH) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.primary_icasei)),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(2000)) + scaleIn(
                initialScale = 0.8f,
                animationSpec = tween(2000)
            ),
            exit = fadeOut(animationSpec = tween(500)) + scaleOut(
                targetScale = 1.2f,
                animationSpec = tween(500)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash),
                contentDescription = "Splash Image",
            )
        }
    }
}
