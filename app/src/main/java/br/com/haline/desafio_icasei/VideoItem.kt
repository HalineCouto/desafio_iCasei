package br.com.haline.desafio_icasei

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.haline.desafio_icasei.data.dataclass.Video

@Composable
fun VideoItem(video: Video) {

    val context = LocalContext.current


    Column(modifier = Modifier.padding(8.dp)) {
        // Título do vídeo
        Text(text = video.snippet.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)

        // Descrição do vídeo
        Text(text = video.snippet.description, fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(8.dp))

        // Exibindo o link do vídeo (exemplo)
        Text(
            text = "Link: https://www.youtube.com/watch?v=${video.id.videoId}",
            fontSize = 14.sp,
            color = Color.Blue,
            modifier = Modifier.clickable {
                // Aqui você pode abrir o link do vídeo, por exemplo
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${video.id.videoId}"))
                context.startActivity(intent)
            }
        )
    }
}
