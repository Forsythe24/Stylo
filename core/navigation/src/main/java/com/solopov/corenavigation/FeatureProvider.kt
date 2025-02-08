package com.solopov.corenavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface AuthFeatureProvider {
    fun NavGraphBuilder.authGraph(controller: NavHostController)
}

interface BottomBarFeatureProvider {
    fun NavGraphBuilder.bottomBarGraph(
        route: BaseRoute.Route,
        mainItems: List<ScreenItem.MainScreenItem>,
        secondaryItems: List<ScreenItem.SecondaryScreenItem>
    )

    sealed interface ScreenItem {
        val route: BaseRoute.Route
        val startDestination: BaseRoute
        val builder: NavGraphBuilder.(NavHostController) -> Unit

        data class MainScreenItem(
            @DrawableRes val iconRes: Int,
            @StringRes val labelRes: Int,
            override val route: BaseRoute.Route,
            override val startDestination: BaseRoute,
            override val builder: NavGraphBuilder.(NavHostController) -> Unit,
        ) : ScreenItem

        data class SecondaryScreenItem(
            override val route: BaseRoute.Route,
            override val startDestination: BaseRoute,
            override val builder: NavGraphBuilder.(NavHostController) -> Unit,
        ) : ScreenItem
    }
}

interface HomeFeatureProvider {
    fun NavGraphBuilder.homeGraph(controller: NavHostController)
}

interface SearchFeatureProvider {
    fun NavGraphBuilder.searchGraph(controller: NavHostController)
}

interface CartFeatureProvider {
    fun NavGraphBuilder.cartGraph(controller: NavHostController)
}

interface FavoritesFeatureProvider {
    fun NavGraphBuilder.favoritesGraph(controller: NavHostController)
}
