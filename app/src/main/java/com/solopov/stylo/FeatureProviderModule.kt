package com.solopov.stylo

import com.solopov.corenavigation.AuthFeatureProvider
import com.solopov.corenavigation.BottomBarFeatureProvider
import com.solopov.corenavigation.CartFeatureProvider
import com.solopov.corenavigation.FavoritesFeatureProvider
import com.solopov.corenavigation.HomeFeatureProvider
import com.solopov.corenavigation.SearchFeatureProvider
import com.solopov.feature_auth_impl.presentation.AuthFeatureProviderImpl
import com.solopov.feature_bottom_bar.BottomBarFeatureProviderImpl
import com.solopov.feature_cart_impl.presentation.CartFeatureProviderImpl
import com.solopov.feature_favorites_impl.presentation.FavoritesFeatureProviderImpl
import com.solopov.feature_home_impl.presentation.HomeFeatureProviderImpl
import com.solopov.feature_search_impl.presentation.SearchFeatureProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FeatureProviderModule {
    @Binds
    @Singleton
    fun bindAuthFeatureProvider(authFeatureProviderImpl: AuthFeatureProviderImpl): AuthFeatureProvider

    @Binds
    @Singleton
    fun bindCartFeatureProvider(cartFeatureProviderImpl: CartFeatureProviderImpl): CartFeatureProvider

    @Binds
    @Singleton
    fun bindHomeFeatureProvider(homeFeatureProviderImpl: HomeFeatureProviderImpl): HomeFeatureProvider

    @Binds
    @Singleton
    fun bindSearchFeatureProvider(searchFeatureProviderImpl: SearchFeatureProviderImpl): SearchFeatureProvider

    @Binds
    @Singleton
    fun bindFavoritesFeatureProvider(favoritesFeatureProviderImpl: FavoritesFeatureProviderImpl): FavoritesFeatureProvider

    @Binds
    @Singleton
    fun bindBottomBarFeatureProvider(bottomBarFeatureProvider: BottomBarFeatureProviderImpl): BottomBarFeatureProvider
}
