package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.framework.api

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.YouTubeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("search")
    suspend fun searchVideos(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("key") apiKey: String
    ): YouTubeResponse

}