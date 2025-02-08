package com.solopov.feature_favorites_api.domain

import com.solopov.feature_favorites_api.domain.model.FavoriteClothing

interface GetAllFavoritesUseCase {
    suspend operator fun invoke(): List<FavoriteClothing>
}
