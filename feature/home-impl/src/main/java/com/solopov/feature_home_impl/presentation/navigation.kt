package com.solopov.feature_home_impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.solopov.corenavigation.AppRoute
import com.solopov.corenavigation.HomeFeatureProvider
import com.solopov.corenavigation.base.composable
import com.solopov.feature_home_impl.presentation.screen.HomeScreen
import javax.inject.Inject

class HomeFeatureProviderImpl @Inject constructor() : HomeFeatureProvider {

    override fun NavGraphBuilder.homeGraph(controller: NavHostController) {
        composable(AppRoute.MainBottomBar.Home) { HomeScreen() }
    }
}
