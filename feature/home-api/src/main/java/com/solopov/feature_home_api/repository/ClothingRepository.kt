package com.solopov.feature_home_api.repository

import com.solopov.feature_home_api.domain.model.Clothing

interface ClothingRepository {
    suspend fun getAllClothing(): List<Clothing>
    suspend fun removeFromFavorites(clothingId: Long)
    suspend fun addToFavorites(clothingId: Long)
}
