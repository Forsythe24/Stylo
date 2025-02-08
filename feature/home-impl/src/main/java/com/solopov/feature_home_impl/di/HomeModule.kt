package com.solopov.feature_home_impl.di

import com.solopov.feature_home_api.domain.usecase.ChangeClothingInFavoritesStateUseCase
import com.solopov.feature_home_api.domain.usecase.GetAllClothingUseCase
import com.solopov.feature_home_api.repository.ClothingRepository
import com.solopov.feature_home_impl.data.repository.ClothingRepositoryImpl
import com.solopov.feature_home_impl.domain.usecase.ChangeClothingInFavoritesStateUseCaseImpl
import com.solopov.feature_home_impl.domain.usecase.GetAllClothingUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface HomeModule {
    @Binds
    @ViewModelScoped
    fun bindClothingRepository(clothingRepositoryImpl: ClothingRepositoryImpl): ClothingRepository

    @Binds
    @ViewModelScoped
    fun bindGetAllClothingUseCase(getAllClothingUseCaseImpl: GetAllClothingUseCaseImpl): GetAllClothingUseCase

    @Binds
    @ViewModelScoped
    fun bindChangeClothingInFavoritesStateUseCase(changeClothingInFavoritesStateUseCaseImpl: ChangeClothingInFavoritesStateUseCaseImpl): ChangeClothingInFavoritesStateUseCase
}
