package com.solopov.feature_favorites_api.domain

interface RemoveFromFavoritesUseCase {
    suspend operator fun invoke(clothingId: Long)
}
