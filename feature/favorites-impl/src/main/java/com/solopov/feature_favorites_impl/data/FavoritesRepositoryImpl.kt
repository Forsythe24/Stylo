package com.solopov.feature_favorites_impl.data

import com.solopov.corecommon.remote.ClothingDataSource
import com.solopov.coredata.database.dao.FavoriteDao
import com.solopov.feature_favorites_api.domain.model.FavoriteClothing
import com.solopov.feature_favorites_api.repository.FavoritesRepository
import com.solopov.feature_favorites_impl.data.mapper.mapToFavoritesList
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val remoteDataSource: ClothingDataSource,
    private val favoriteDao: FavoriteDao,
) : FavoritesRepository {

    override suspend fun getAllFavoriteClothing(): List<FavoriteClothing> {
        val favoritesIds = favoriteDao.getAll().map { it.clothingId }
        val allClothing = remoteDataSource.getAllClothing()
        val favoriteClothingList = allClothing.filter {
            favoritesIds.contains(it.id)
        }
        return favoriteClothingList.mapToFavoritesList()
    }

    override suspend fun removeFromFavorites(clothingId: Long) {
        favoriteDao.deleteById(clothingId)
    }
}
