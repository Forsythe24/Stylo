package com.solopov.feature_home_api.domain.usecase

interface ChangeClothingInFavoritesStateUseCase {
    suspend operator fun invoke(clothingId: Long, newState: Boolean)
}
