package com.solopov.feature_home_impl.domain.usecase

import com.solopov.coredata.coroutine.IoDispatcher
import com.solopov.feature_home_api.domain.usecase.ChangeClothingInFavoritesStateUseCase
import com.solopov.feature_home_api.repository.ClothingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangeClothingInFavoritesStateUseCaseImpl @Inject constructor(
    private val clothingRepository: ClothingRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) : ChangeClothingInFavoritesStateUseCase {
    override suspend fun invoke(clothingId: Long, newState: Boolean) {
        withContext(coroutineDispatcher) {
            if (newState) {
                clothingRepository.addToFavorites(clothingId)
            } else {
                clothingRepository.removeFromFavorites(clothingId)
            }
        }
    }
}
