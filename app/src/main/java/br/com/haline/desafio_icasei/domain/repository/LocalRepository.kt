package br.com.haline.desafio_icasei.domain.repository

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.data.local.dao.PlaylistVideoDao
import br.com.haline.desafio_icasei.data.dataclass.FavoritesList
import br.com.haline.desafio_icasei.data.dataclass.Snippet
import br.com.haline.desafio_icasei.data.dataclass.Thumbnail
import br.com.haline.desafio_icasei.data.dataclass.Thumbnails
import br.com.haline.desafio_icasei.data.dataclass.Video
import br.com.haline.desafio_icasei.data.dataclass.VideoId
import br.com.haline.desafio_icasei.data.local.dao.VideoDao
import br.com.haline.desafio_icasei.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.data.local.entities.PlaylistEntity
import br.com.haline.desafio_icasei.data.local.entities.PlaylistVideoEntity
import br.com.haline.desafio_icasei.domain.model.FavoriteVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LocalRepository(
    private val videoDao: VideoDao,
    private val playlistVideoDao: PlaylistVideoDao
) {

    suspend fun addFavoriteVideo(favoriteVideo: FavoriteVideoEntity) {
        videoDao.insertFavoriteVideo(favoriteVideo)
    }

    suspend fun getFavoriteVideos(): List<FavoriteVideoEntity> {
        return videoDao.getAllFavoriteVideos()
    }

    suspend fun addPlaylist(playlist: PlaylistEntity) {
        playlistVideoDao.insertPlaylist(playlist)
    }

    fun getPlaylists(): Flow<List<PlaylistEntity>> {
        return playlistVideoDao.getAllPlaylists()
    }

    suspend fun removeFavoriteVideo(videoId: String) {
        videoDao.removeFavoriteVideoById(videoId)
    }

    fun isFavorite(videoId: String): Flow<Boolean> {
        return videoDao.getFavoriteVideoById(videoId)
            .map { it != null }
    }

    suspend fun addVideoToPlaylist(playlistVideo: PlaylistVideoEntity) {
        if (!isVideoInPlaylist(playlistVideo.playlistId)) {
            playlistVideoDao.insertVideoToPlaylist(playlistVideo)
        }
    }

    suspend fun getVideosForPlaylist(playlistId: String): List<FavoritesList> {
        val videoEntities = playlistVideoDao.getVideosForPlaylist(playlistId)
        return videoEntities.map { entity ->
            FavoritesList(
                videoId = entity.videoId,
                title = entity.title,
                description = entity.description,
                thumbnailUrl = entity.thumbnailUrl
            )
        }
    }


    private suspend fun isVideoInPlaylist(playlistId: String): Boolean {
        return (playlistVideoDao.getVideosForPlaylist(playlistId).isEmpty())

    }

    suspend fun removeVideoFromPlaylist(playlistId: String, videoId: String) {
        playlistVideoDao.removeVideoFromPlaylist(playlistId, videoId)
    }
}
