package br.com.haline.desafio_icasei.presentation.composable

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.MainActivity
import br.com.haline.desafio_icasei.R

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título ou algum cabeçalho
        Text(text = "Bem-vindo ao YouTube App", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(32.dp))

        // Botões de navegação
        Button(onClick = {
            navController.navigate("youtube_screen") // Navega para a tela de busca
        }) {
            Text("Buscar vídeos no YouTube")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("favorite_video") // Navega para a tela de vídeos favoritos
        }) {
            Text("Vídeos Favoritos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("video_player/{videoId}") // Navega para a tela de listas de reprodução
        }) {
            Text("Listas de Reprodução")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("terms_of_use") // Navega para o WebView dos termos de uso
        }) {
            Text("Termos de Uso do YouTube")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val videoId = "lDK9QqIzhwk" // substitua pelo id do vídeo desejado
            sendVideoNotification(context, videoId)
        }) {
            Text(text = "Enviar Notificação de Vídeo")
        }
    }
}

fun sendVideoNotification(context: Context, videoId: String) {
    val channelId = "video_channel_id"
    val notificationId = 1001

    // Cria uma intent para abrir o MainActivity com o extra "videoId"
    val intent = Intent(context, MainActivity::class.java).apply {
        putExtra("videoId", videoId)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Cria a notificação
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_google_logo) // Substitua por um ícone real do seu app
        .setContentTitle("Novo vídeo disponível")
        .setContentText("Clique para assistir ao vídeo!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    // Exibe a notificação
    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notify(notificationId, builder.build())
    }
}