package com.solopov.corenavigation

sealed class BaseRoute(open val route: String) {
    open class Route(override val route: String) : BaseRoute(route)
    open class RouteWithArg(override val route: String) : BaseRoute(route)
}

object AppRoute {
    object MainBottomBar : BaseRoute.Route("MainBottomBar") {
        object HomeTab : Route("HomeTab")
        object SearchTab : Route("SearchTab")
        object CartTab : Route("CartTab")
        object FavoritesTab : Route("FavoritesTab")
        object Home : Route("Home")
        object Search : Route("Search")
        object Cart : Route("Cart")
        object Favorites : Route("Cart")
    }

    object Auth : BaseRoute.Route("Auth")
}
