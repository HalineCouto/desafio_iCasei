package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.domain.repository

import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.dao.PlaylistVideoDao
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.dao.VideoDao
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistVideoEntity
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
        val playlistExists = playlistVideoDao.getPlaylistById(playlistVideo.playlistId) != null
        val favoriteVideoExists = playlistVideoDao.getFavoriteVideoByIdSync(playlistVideo.videoId) != null

        if (playlistExists && favoriteVideoExists && !isVideoInPlaylist(playlistVideo.playlistId, playlistVideo.videoId)) {
            playlistVideoDao.insertVideoToPlaylist(playlistVideo)
        }
    }



    private suspend fun isVideoInPlaylist(playlistId: String, videoId: String): Boolean {
        return playlistVideoDao.isVideoInPlaylist(playlistId, videoId)
    }

    suspend fun getVideosForPlaylist(playlistId: String): List<FavoriteVideoEntity> {
        val videoEntities = playlistVideoDao.getVideosForPlaylist(playlistId)
        return videoEntities.map { entity ->
            FavoriteVideoEntity(
                videoId = entity.videoId,
                title = entity.title,
                description = entity.description,
                thumbnailUrl = entity.thumbnailUrl
            )
        }
    }

    suspend fun removeVideoFromPlaylist(playlistId: String, videoId: String) {
        playlistVideoDao.removeVideoFromPlaylist(playlistId, videoId)
    }
}
