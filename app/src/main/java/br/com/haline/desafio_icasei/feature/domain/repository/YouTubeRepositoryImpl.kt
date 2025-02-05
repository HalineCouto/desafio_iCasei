package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.dataclass.Video
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.remote.YouTubeDataSource

class YouTubeRepositoryImpl(private val dataSource: YouTubeDataSource) : YouTubeRepository {
    override suspend fun searchVideos(query: String, apiKey: String): List<Video> {
        return dataSource.searchVideos(query, apiKey)
    }
}