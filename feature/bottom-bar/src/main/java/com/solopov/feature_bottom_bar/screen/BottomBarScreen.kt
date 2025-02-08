package com.solopov.feature_bottom_bar.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solopov.corenavigation.AppRoute
import com.solopov.corenavigation.BottomBarFeatureProvider
import com.solopov.corenavigation.base.createBaseRoute
import com.solopov.corenavigation.base.makeRoute
import com.solopov.coreuicompose.uikit.BOTTOM_BAR_HEIGHT
import com.solopov.coreuicompose.uikit.BOTTOM_BAR_HEIGHT_GONE
import com.solopov.coreuicompose.uikit.BottomNavigationDefaults
import com.solopov.coreuicompose.uikit.LocalBottomNavigationHeight
import com.solopov.coreuicompose.uikit.StyloBottomNavigation
import com.solopov.coreuitheme.compose.AppTheme
import com.solopov.coreuitheme.compose.Dimen
import com.solopov.coruitheme.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarScreen(
    mainItems: List<BottomBarFeatureProvider.ScreenItem.MainScreenItem>,
    secondaryItems: List<BottomBarFeatureProvider.ScreenItem.SecondaryScreenItem>,
) {
    val navController = rememberNavController()
    val firstRootRoute = AppRoute.MainBottomBar.HomeTab
    val systemUiController = rememberSystemUiController()
    val defaultNavigationBarColor = AppTheme.colorScheme.primaryContainer

    systemUiController.setNavigationBarColor(defaultNavigationBarColor)
    systemUiController.setStatusBarColor(Color.Transparent, AppTheme.isLightTheme)

    Scaffold(
        bottomBar = { BottomBar(navController, mainItems) }
    ) { paddingValues ->
        CompositionLocalProvider(
            LocalBottomNavigationHeight provides BottomNavigationDefaults.Height,
        ) {
            NavHost(
                navController = navController,
                startDestination = makeRoute(firstRootRoute),
                modifier = Modifier.padding(paddingValues)
            ) {
                mainItems.forEach {
                    initItems(navController, it)
                }
                secondaryItems.forEach {
                    initItems(navController, it)
                }
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    mainItems: List<BottomBarFeatureProvider.ScreenItem.MainScreenItem>,
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val bottomBarScreens = listOf(
        AppRoute.MainBottomBar.Home,
        AppRoute.MainBottomBar.Search,
        AppRoute.MainBottomBar.Cart,
    )
    val bottomBarDestination =
        bottomBarScreens.any { it.route == currentDestination?.route?.substringBefore(delimiter = "?") }
    BottomNavigationDefaults.Height =
        if (bottomBarDestination) BOTTOM_BAR_HEIGHT else BOTTOM_BAR_HEIGHT_GONE
    if (bottomBarDestination) {
        StyloBottomBar(
            navController = navController,
            mainItems = mainItems,
            currentDestination = currentDestination,
        )
    }
}

@Composable
private fun StyloBottomBar(
    navController: NavController,
    currentDestination: NavDestination?,
    mainItems: List<BottomBarFeatureProvider.ScreenItem.MainScreenItem>,
) {
    StyloBottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
        contentPadding = WindowInsets.navigationBars
            .asPaddingValues(),
    ) {
        mainItems.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.route == makeRoute(screen.route)
            } == true

            NavigationBarItem(
                modifier = Modifier
                    .padding(Dimen.padding_4)
                    .clip(RoundedCornerShape(Dimen.radius_medium)),
                icon = {
                    Icon(
                        painterResource(screen.iconRes),
                        contentDescription = stringResource(screen.labelRes),
                        tint = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outline
                        }
                    )
                },
                selected = isSelected,
                onClick = {
                    if (isSwitchAvailable(screen, currentDestination, mainItems)) {
                        navController.switchTo(makeRoute(screen.route))
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.outline,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.outline,
                )
            )
        }
    }
}

private fun NavGraphBuilder.initItems(
    navController: NavHostController,
    screenItem: BottomBarFeatureProvider.ScreenItem
) {
    navigation(
        route = makeRoute(screenItem.route),
        startDestination = createBaseRoute(screenItem.startDestination)
    ) {
        with(screenItem) { builder(navController) }
    }
}

private fun NavController.switchTo(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}

/** The transition is available if it:
 * 1) is carried out between tabs
 * 2) is carried out to the same tab, but only from a screen that isn't the start destination
 **/
private fun isSwitchAvailable(
    screen: BottomBarFeatureProvider.ScreenItem.MainScreenItem,
    currentDestination: NavDestination?,
    mainItems: List<BottomBarFeatureProvider.ScreenItem.MainScreenItem>
): Boolean {
    return makeRoute(screen.route) != currentDestination?.parent?.route ||
            mainItems.none { createBaseRoute(it.startDestination) == currentDestination.route }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    AppTheme {
        BottomNavigationDefaults.Height = BOTTOM_BAR_HEIGHT
        StyloBottomBar(
            navController = rememberNavController(),
            currentDestination = null,
            mainItems = listOf(
                BottomBarFeatureProvider.ScreenItem.MainScreenItem(
                    iconRes = R.drawable.ic_home,
                    labelRes = com.solopov.corecommon.R.string.home_title,
                    route = AppRoute.MainBottomBar.HomeTab,
                    startDestination = AppRoute.MainBottomBar.Home,
                    builder = { }
                ),
                BottomBarFeatureProvider.ScreenItem.MainScreenItem(
                    iconRes = R.drawable.ic_search,
                    labelRes = com.solopov.corecommon.R.string.search_title,
                    route = AppRoute.MainBottomBar.SearchTab,
                    startDestination = AppRoute.MainBottomBar.Search,
                    builder = { },
                ),
                BottomBarFeatureProvider.ScreenItem.MainScreenItem(
                    iconRes = R.drawable.ic_cart,
                    labelRes = com.solopov.corecommon.R.string.cart_title,
                    route = AppRoute.MainBottomBar.CartTab,
                    startDestination = AppRoute.MainBottomBar.Cart,
                    builder = { },
                ),
            ),
        )
    }
}
