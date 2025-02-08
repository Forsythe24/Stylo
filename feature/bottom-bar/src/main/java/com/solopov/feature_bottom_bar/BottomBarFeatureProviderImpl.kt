package com.solopov.feature_bottom_bar

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solopov.corenavigation.BaseRoute
import com.solopov.corenavigation.BottomBarFeatureProvider
import com.solopov.corenavigation.base.makeRoute
import com.solopov.feature_bottom_bar.screen.BottomBarScreen
import javax.inject.Inject

class BottomBarFeatureProviderImpl @Inject constructor() : BottomBarFeatureProvider {

    override fun NavGraphBuilder.bottomBarGraph(
        route: BaseRoute.Route,
        mainItems: List<BottomBarFeatureProvider.ScreenItem.MainScreenItem>,
        secondaryItems: List<BottomBarFeatureProvider.ScreenItem.SecondaryScreenItem>,
    ) {
        composable(route = makeRoute(route)) { BottomBarScreen(mainItems, secondaryItems) }
    }
}
