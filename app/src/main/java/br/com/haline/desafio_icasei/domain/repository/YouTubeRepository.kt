package br.com.haline.desafio_icasei.domain.repository

import android.util.Log
import br.com.haline.desafio_icasei.RetrofitInstance
import br.com.haline.desafio_icasei.data.dataclass.Video
import com.google.gson.Gson


class YouTubeRepository {


    private val youTubeApiService = RetrofitInstance.youtubeApiService

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
