package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.remote

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video

interface YouTubeDataSource {
    suspend fun searchVideos(query: String, apiKey: String): List<Video>
}