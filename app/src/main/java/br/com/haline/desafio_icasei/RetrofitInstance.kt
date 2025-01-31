package br.com.haline.desafio_icasei

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    // Criando o cliente HTTP com OkHttp
    private val client = OkHttpClient.Builder().build()

    // Criação da instância do Retrofit
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // URL base
            .addConverterFactory(GsonConverterFactory.create())  // Conversão de JSON para objeto
            .client(client)  // Usando o OkHttpClient
            .build()
    }

    // Criando o serviço de API que será utilizado
    val youtubeApiService: YouTubeApiService by lazy {
        retrofit.create(YouTubeApiService::class.java)
    }
}