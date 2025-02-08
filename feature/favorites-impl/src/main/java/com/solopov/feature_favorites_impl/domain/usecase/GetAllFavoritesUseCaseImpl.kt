package com.solopov.feature_favorites_impl.domain.usecase

import com.solopov.coredata.coroutine.IoDispatcher
import com.solopov.feature_favorites_api.domain.GetAllFavoritesUseCase
import com.solopov.feature_favorites_api.domain.model.FavoriteClothing
import com.solopov.feature_favorites_api.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllFavoritesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
): GetAllFavoritesUseCase {
    override suspend fun invoke(): List<FavoriteClothing> {
        return withContext(coroutineDispatcher) {
            favoritesRepository.getAllFavoriteClothing()
        }
    }
}
