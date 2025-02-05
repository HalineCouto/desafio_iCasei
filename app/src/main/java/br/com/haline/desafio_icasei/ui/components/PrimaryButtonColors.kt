package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.ui.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import br.com.haline.desafio_icasei.R

@Composable
fun PrimaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorResource(R.color.primary_icasei),
    contentColor = Color.White
)