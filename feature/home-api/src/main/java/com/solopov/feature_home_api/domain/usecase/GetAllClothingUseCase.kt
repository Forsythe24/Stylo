package com.solopov.feature_home_api.domain.usecase

import com.solopov.feature_home_api.domain.model.Clothing

interface GetAllClothingUseCase {
    suspend operator fun invoke(): List<Clothing>
}
