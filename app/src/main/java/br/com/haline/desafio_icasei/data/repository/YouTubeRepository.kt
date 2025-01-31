package br.com.haline.desafio_icasei.data.repository

import br.com.haline.desafio_icasei.RetrofitInstance
import br.com.haline.desafio_icasei.data.dataclass.Video


class YouTubeRepository {


    private val youTubeApiService = RetrofitInstance.youtubeApiService

    suspend fun searchVideos(query: String, apiKey: String): List<Video> {
        try {
            val response = youTubeApiService.searchVideos(query = query, apiKey = apiKey)
            return response.items
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }
}
