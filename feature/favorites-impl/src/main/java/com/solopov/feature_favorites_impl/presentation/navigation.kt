package com.solopov.feature_favorites_impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.solopov.corenavigation.AppRoute
import com.solopov.corenavigation.CartFeatureProvider
import com.solopov.corenavigation.FavoritesFeatureProvider
import com.solopov.corenavigation.base.composable
import com.solopov.feature_favorites_impl.presentation.screen.FavoritesScreen
import javax.inject.Inject

class FavoritesFeatureProviderImpl @Inject constructor() : FavoritesFeatureProvider {

    override fun NavGraphBuilder.favoritesGraph(controller: NavHostController) {
        composable(AppRoute.MainBottomBar.Favorites) { FavoritesScreen() }
    }
}
