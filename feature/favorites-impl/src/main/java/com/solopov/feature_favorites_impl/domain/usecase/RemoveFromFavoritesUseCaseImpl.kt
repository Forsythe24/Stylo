package com.solopov.feature_favorites_impl.domain.usecase

import com.solopov.coredata.coroutine.IoDispatcher
import com.solopov.feature_favorites_api.domain.RemoveFromFavoritesUseCase
import com.solopov.feature_favorites_api.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoveFromFavoritesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : RemoveFromFavoritesUseCase {

    override suspend fun invoke(clothingId: Long) {
        withContext(coroutineDispatcher) {
            favoritesRepository.removeFromFavorites(clothingId)
        }
    }
}
