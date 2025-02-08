package com.solopov.feature_home_impl.domain.usecase

import com.solopov.coredata.coroutine.IoDispatcher
import com.solopov.feature_home_api.domain.model.Clothing
import com.solopov.feature_home_api.domain.usecase.GetAllClothingUseCase
import com.solopov.feature_home_api.repository.ClothingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllClothingUseCaseImpl @Inject constructor(
    private val clothingRepository: ClothingRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
): GetAllClothingUseCase {
    override suspend fun invoke(): List<Clothing> {
        return withContext(coroutineDispatcher) {
            clothingRepository.getAllClothing()
        }
    }
}
