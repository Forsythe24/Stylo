package com.solopov.feature_favorites_api.repository

import com.solopov.feature_favorites_api.domain.model.FavoriteClothing


interface FavoritesRepository {
    suspend fun getAllFavoriteClothing(): List<FavoriteClothing>
    suspend fun removeFromFavorites(clothingId: Long)
}
