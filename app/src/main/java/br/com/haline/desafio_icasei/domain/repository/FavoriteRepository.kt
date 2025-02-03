package br.com.haline.desafio_icasei.domain.repository



class FavoriteRepository {


    private val favoriteVideoIds = mutableSetOf<String>()

    // Função para adicionar um ID de vídeo aos favoritos
    fun addFavorite(videoId: String) {
        favoriteVideoIds.add(videoId)
    }

    // Função para remover um ID de vídeo dos favoritos
    fun removeFavorite(videoId: String) {
        favoriteVideoIds.remove(videoId)
    }

    // Função para obter todos os IDs dos vídeos favoritos
    fun getFavoriteVideos(): Set<String> = favoriteVideoIds

    // Função para verificar se um vídeo é favorito
    fun isFavorite(videoId: String): Boolean = favoriteVideoIds.contains(videoId)
}
