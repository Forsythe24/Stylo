package com.solopov.feature_favorites_impl.di

import com.solopov.feature_favorites_api.domain.RemoveFromFavoritesUseCase
import com.solopov.feature_favorites_api.domain.GetAllFavoritesUseCase
import com.solopov.feature_favorites_api.repository.FavoritesRepository
import com.solopov.feature_favorites_impl.domain.usecase.RemoveFromFavoritesUseCaseImpl
import com.solopov.feature_favorites_impl.domain.usecase.GetAllFavoritesUseCaseImpl
import com.solopov.feature_favorites_impl.data.FavoritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface FavoritesModule {
    @Binds
    @ViewModelScoped
    fun bindFavoritesRepository(favoritesRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    @ViewModelScoped
    fun bindGetAllFavoritesUseCase(getAllFavoritesUseCaseImpl: GetAllFavoritesUseCaseImpl): GetAllFavoritesUseCase

    @Binds
    @ViewModelScoped
    fun bindRemoveFromFavoritesUseCase(removeFromFavoritesUseCaseImpl: RemoveFromFavoritesUseCaseImpl): RemoveFromFavoritesUseCase
}
