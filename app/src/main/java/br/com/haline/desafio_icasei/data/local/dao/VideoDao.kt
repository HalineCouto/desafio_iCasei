package br.com.haline.desafio_icasei.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.haline.desafio_icasei.data.local.entities.FavoriteVideoEntity
import br.com.haline.desafio_icasei.data.local.entities.PlaylistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteVideo(favoriteVideo: FavoriteVideoEntity)

    @Query("SELECT * FROM favorite_videos")
    suspend fun getAllFavoriteVideos(): List<FavoriteVideoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity)

    @Query("SELECT * FROM playlists")
    suspend fun getAllPlaylists(): List<PlaylistEntity>

    @Query("DELETE FROM favorite_videos WHERE videoId = :videoId")
    suspend fun removeFavoriteVideoById(videoId: String)

    @Query("SELECT * FROM favorite_videos WHERE videoId = :videoId LIMIT 1")
    fun getFavoriteVideoById(videoId: String): Flow<FavoriteVideoEntity?>
}