package br.com.haline.desafio_icasei

import br.com.haline.desafio_icasei.data.dataclass.YouTubeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("search")
    suspend fun searchVideos(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("key") apiKey: String
    ): YouTubeResponse

}