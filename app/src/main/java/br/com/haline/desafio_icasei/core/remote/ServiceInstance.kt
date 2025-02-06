package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.core.remote

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.framework.api.YouTubeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceInstance {

    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    private val client = OkHttpClient.Builder().build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val youtubeApi: YouTubeApi by lazy {
        retrofit.create(YouTubeApi::class.java)
    }
}