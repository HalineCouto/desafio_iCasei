package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository

import android.util.Log
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.remote.ServiceInstance
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import com.google.gson.Gson


interface YouTubeRepository {
    suspend fun searchVideos(query: String, apiKey: String): List<Video>
}
