package br.com.haline.desafio_icasei.domain.repository

import android.util.Log
import br.com.haline.desafio_icasei.data.local.dao.VideoDao
import br.com.haline.desafio_icasei.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.data.local.entities.PlaylistEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LocalRepository(private val videoDao: VideoDao) {

    suspend fun addFavoriteVideo(favoriteVideo: FavoriteVideoEntity) {
        videoDao.insertFavoriteVideo(favoriteVideo)
    }

    suspend fun getFavoriteVideos(): List<FavoriteVideoEntity> {
        return videoDao.getAllFavoriteVideos()
    }


    suspend fun addPlaylist(playlist: PlaylistEntity) {
        videoDao.insertPlaylist(playlist)
    }

    suspend fun getPlaylists(): List<PlaylistEntity> {
        return videoDao.getAllPlaylists()
    }
    suspend fun removeFavoriteVideo(videoId: String) {
        videoDao.removeFavoriteVideoById(videoId)
    }

    fun isFavorite(videoId: String): Flow<Boolean> {
        return videoDao.getFavoriteVideoById(videoId)
            .map { it != null }
    }
}