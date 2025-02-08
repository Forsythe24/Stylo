package com.solopov.feature_search_impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.solopov.corenavigation.AppRoute
import com.solopov.corenavigation.SearchFeatureProvider
import com.solopov.corenavigation.base.composable
import com.solopov.feature_search_impl.presentation.screen.SearchScreen
import javax.inject.Inject

class SearchFeatureProviderImpl @Inject constructor() : SearchFeatureProvider {

    override fun NavGraphBuilder.searchGraph(controller: NavHostController) {
        composable(AppRoute.MainBottomBar.Search) { SearchScreen() }
    }
}
