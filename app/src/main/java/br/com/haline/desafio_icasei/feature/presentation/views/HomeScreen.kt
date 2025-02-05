package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.haline.desafio_icasei.R
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_YOUTUBE_FAVORITES
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_YOUTUBE_OPEN_NOTIFICATION
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_YOUTUBE_PLAYLIST
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_YOUTUBE_SEARCH
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.navigation.ROUT_YOUTUBE_TERMS
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.ui.components.PrimaryButtonColors

@Composable
fun HomeScreen(
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(R.string.home_welcome),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))


            Button(
                onClick = { navController.navigate(ROUT_YOUTUBE_SEARCH) },
                colors = PrimaryButtonColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.home_btn_search), fontSize = 18.sp)
            }

            Button(
                onClick = { navController.navigate(ROUT_YOUTUBE_FAVORITES) },
                colors = PrimaryButtonColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.home_btn_favorites), fontSize = 18.sp)
            }

            Button(
                onClick = { navController.navigate(ROUT_YOUTUBE_PLAYLIST) },
                colors = PrimaryButtonColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.home_btn_playlist), fontSize = 18.sp)
            }

            Button(
                onClick = { navController.navigate(ROUT_YOUTUBE_OPEN_NOTIFICATION) },
                colors = PrimaryButtonColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.home_btn_notification), fontSize = 18.sp)
            }

            Button(
                onClick = { navController.navigate(ROUT_YOUTUBE_TERMS) },
                colors = PrimaryButtonColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.home_btn_terms), fontSize = 18.sp)
            }
        }
    }
}


//
//
//
//Column(
//modifier = Modifier
//.fillMaxSize()
//.padding(16.dp),
//verticalArrangement = Arrangement.Center,
//horizontalAlignment = Alignment.CenterHorizontally
//) {
//
//    Text(text = "Bem-vindo ao YouTube App", style = MaterialTheme.typography.bodySmall)
//
//    Spacer(modifier = Modifier.height(32.dp))
//
//    Button(onClick = {
//        navController.navigate("youtube_screen") // Navega para a tela de busca
//    }) {
//        Text("Buscar vídeos no YouTube")
//    }
//
//    Spacer(modifier = Modifier.height(16.dp))
//
//    Button(onClick = {
//        navController.navigate("favorite_video") // Navega para a tela de vídeos favoritos
//    }) {
//        Text("Vídeos Favoritos")
//    }
//
//    Spacer(modifier = Modifier.height(16.dp))
//
//    Button(onClick = {
//        navController.navigate("playlist_screen") // Navega para a tela de listas de reprodução
//    }) {
//        Text("Listas de Reprodução")
//    }
//
//    Spacer(modifier = Modifier.height(16.dp))
//
//    Button(onClick = {
//        navController.navigate("terms_of_use") // Navega para o WebView dos termos de uso
//    }) {
//        Text("Termos de Uso do YouTube")
//    }
//
//    Spacer(modifier = Modifier.height(16.dp))
//
//    Button(onClick = {
//        val videoId = "lDK9QqIzhwk" // substitua pelo id do vídeo desejado
//        sendVideoNotification(context, videoId)
//    }) {
//        Text(text = "Enviar Notificação de Vídeo")
//    }
//}
//}
//
//fun sendVideoNotification(context: Context, videoId: String) {
//    val channelId = "video_channel_id"
//    val notificationId = 1001
//
//    // Cria uma intent para abrir o MainActivity com o extra "videoId"
//    val intent = Intent(context, MainActivity::class.java).apply {
//        putExtra("videoId", videoId)
//        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//    }
//
//    val pendingIntent = PendingIntent.getActivity(
//        context,
//        0,
//        intent,
//        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//    )
//
//    // Cria a notificação
//    val builder = NotificationCompat.Builder(context, channelId)
//        .setSmallIcon(R.drawable.ic_google_logo) // Substitua por um ícone real do seu app
//        .setContentTitle("Novo vídeo disponível")
//        .setContentText("Clique para assistir ao vídeo!")
//        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//        .setContentIntent(pendingIntent)
//        .setAutoCancel(true)
//
//    // Exibe a notificação
//    with(NotificationManagerCompat.from(context)) {
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        notify(notificationId, builder.build())
//    }
//}