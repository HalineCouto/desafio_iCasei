package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository

import android.util.Log
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.remote.ServiceInstance
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import com.google.gson.Gson


class YouTubeRepository {

    private val youTubeApiService = ServiceInstance.youtubeApi

    suspend fun searchVideos(query: String, apiKey: String): List<Video> {
        try {
            val response = youTubeApiService.searchVideos(query = query, apiKey = apiKey)
            Log.d("YouTubeResponse", "Recebido: ${Gson().toJson(response)}")
            return response.items
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

}
