package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistEntity
import br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.data.local.entities.PlaylistVideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistVideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoToPlaylist(playlistVideo: PlaylistVideoEntity)

    @Query("SELECT * FROM playlist_videos WHERE playlistId = :playlistId")
    suspend fun getVideosForPlaylist(playlistId: String): List<PlaylistVideoEntity>

    @Query("SELECT COUNT(*) > 0 FROM playlist_videos WHERE playlistId = :playlistId AND videoId = :videoId")
    suspend fun isVideoInPlaylist(playlistId: String, videoId: String): Boolean

    @Query("DELETE FROM playlist_videos WHERE playlistId = :playlistId AND videoId = :videoId")
    suspend fun removeVideoFromPlaylist(playlistId: String, videoId: String)

    @Query("SELECT * FROM playlists")
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>

    @Query("SELECT * FROM favorite_videos WHERE videoId = :videoId LIMIT 1")
    suspend fun getFavoriteVideoByIdSync(videoId: String): FavoriteVideoEntity?

    @Query("SELECT * FROM playlists WHERE playlistId = :playlistId LIMIT 1")
    suspend fun getPlaylistById(playlistId: String): PlaylistEntity?

}
