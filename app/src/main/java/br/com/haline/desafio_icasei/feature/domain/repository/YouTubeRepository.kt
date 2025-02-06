package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video

interface YouTubeRepository {
    suspend fun searchVideos(query: String, apiKey: String): List<Video>
}
