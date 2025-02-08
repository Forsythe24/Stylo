package com.solopov.stylo.mainscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.solopov.corenavigation.AppRoute
import com.solopov.corenavigation.AuthFeatureProvider
import com.solopov.corenavigation.BaseRoute
import com.solopov.corenavigation.BottomBarFeatureProvider
import com.solopov.corenavigation.CartFeatureProvider
import com.solopov.corenavigation.FavoritesFeatureProvider
import com.solopov.corenavigation.HomeFeatureProvider
import com.solopov.corenavigation.SearchFeatureProvider
import com.solopov.corenavigation.base.makeRoute
import com.solopov.stylo.model.AppAction
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

import com.solopov.corecommon.R as CoreCommonR
import com.solopov.coruitheme.R as CoreUiThemeR



@AndroidEntryPoint
class AppActivity : ComponentActivity() {
    @Inject
    lateinit var authFeatureProvider: AuthFeatureProvider

    @Inject
    lateinit var bottomBarFeatureProvider: BottomBarFeatureProvider

    @Inject
    lateinit var homeFeatureProvider: HomeFeatureProvider

    @Inject
    lateinit var searchFeatureProvider: SearchFeatureProvider

    @Inject
    lateinit var cartFeatureProvider: CartFeatureProvider

    @Inject
    lateinit var favoritesFeatureProvider: FavoritesFeatureProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<AppViewModel>()
            com.solopov.coreuitheme.compose.AppTheme {
                val navController = rememberNavController()
                val initialScreenAction by viewModel.initialScreenAction.collectAsState()
                initialScreenAction?.let {
                    val startDestination = when (it) {
                        is AppAction.OpenAuthScreen -> AppRoute.Auth
                        is AppAction.OpenAppScreen -> AppRoute.MainBottomBar
                    }
                    NavHost(navController, startDestination)
                }
            }
        }
    }

    @Composable
    private fun NavHost(navController: NavHostController, start: BaseRoute.Route) {
        androidx.navigation.compose.NavHost(
            navController = navController,
            startDestination = makeRoute(start)
        ) {
            with(authFeatureProvider) {
                authGraph(navController)
            }

            with(bottomBarFeatureProvider) {
                bottomBarGraph(AppRoute.MainBottomBar, mainItems(), secondaryItems())
            }
        }
    }

    private fun mainItems() = listOf(
        BottomBarFeatureProvider.ScreenItem.MainScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_home,
            labelRes = CoreCommonR.string.home_title,
            route = AppRoute.MainBottomBar.HomeTab,
            startDestination = AppRoute.MainBottomBar.Home,
            builder = { homeGraph(it) }
        ),
        BottomBarFeatureProvider.ScreenItem.MainScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_favorites,
            labelRes = CoreCommonR.string.favorites_title,
            route = AppRoute.MainBottomBar.FavoritesTab,
            startDestination = AppRoute.MainBottomBar.Favorites,
            builder = { favoritesGraph(it) },
        ),
        BottomBarFeatureProvider.ScreenItem.MainScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_search,
            labelRes = CoreCommonR.string.search_title,
            route = AppRoute.MainBottomBar.SearchTab,
            startDestination = AppRoute.MainBottomBar.Search,
            builder = { searchGraph(it) },
        ),
        BottomBarFeatureProvider.ScreenItem.MainScreenItem(
            iconRes = CoreUiThemeR.drawable.ic_cart,
            labelRes = CoreCommonR.string.cart_title,
            route = AppRoute.MainBottomBar.CartTab,
            startDestination = AppRoute.MainBottomBar.Cart,
            builder = { cartGraph(it) },
        ),
    )

    private fun secondaryItems() = listOf<BottomBarFeatureProvider.ScreenItem.SecondaryScreenItem>()

    private fun NavGraphBuilder.homeGraph(controller: NavHostController) {
        with(homeFeatureProvider) { homeGraph(controller) }
    }

    private fun NavGraphBuilder.searchGraph(controller: NavHostController) {
        with(searchFeatureProvider) { searchGraph(controller) }
    }

    private fun NavGraphBuilder.cartGraph(controller: NavHostController) {
        with(cartFeatureProvider) { cartGraph(controller) }
    }

    private fun NavGraphBuilder.favoritesGraph(controller: NavHostController) {
        with(favoritesFeatureProvider) { favoritesGraph(controller) }
    }
}


