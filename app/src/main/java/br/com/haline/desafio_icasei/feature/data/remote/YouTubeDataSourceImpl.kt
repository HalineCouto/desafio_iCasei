package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.remote

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.framework.api.YouTubeApi

class YouTubeDataSourceImpl(
    private val api: YouTubeApi
) : YouTubeDataSource {
    override suspend fun searchVideos(query: String, apiKey: String): List<Video> {
        return try {
            val response = api.searchVideos(query = query, apiKey = apiKey)
            response.items
        } catch (e: Exception) {
            emptyList()
        }
    }
}