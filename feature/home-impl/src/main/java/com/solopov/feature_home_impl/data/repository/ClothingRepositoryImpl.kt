package com.solopov.feature_home_impl.data.repository

import com.solopov.corecommon.remote.ClothingDataSource
import com.solopov.coredata.database.dao.FavoriteDao
import com.solopov.coredata.database.entity.FavoriteEntity
import com.solopov.feature_home_api.domain.model.Clothing
import com.solopov.feature_home_api.repository.ClothingRepository
import javax.inject.Inject

import com.solopov.feature_home_impl.data.mapper.mapList

class ClothingRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val remoteDataSource: ClothingDataSource
) : ClothingRepository {
    override suspend fun getAllClothing(): List<Clothing> {
        val allClothing = remoteDataSource.getAllClothing()
        val allFavoritesIds = favoriteDao.getAll().map { it.clothingId }
        val allClothingWithRespectToFavorites = allClothing.map {
            if (allFavoritesIds.contains(it.id)) it.copy(isFavorite = true) else it
        }.mapList()
        return allClothingWithRespectToFavorites
    }

    override suspend fun removeFromFavorites(clothingId: Long) {
        favoriteDao.deleteById(clothingId)
    }

    override suspend fun addToFavorites(clothingId: Long) {
        favoriteDao.insert(FavoriteEntity(clothingId))
    }
}
