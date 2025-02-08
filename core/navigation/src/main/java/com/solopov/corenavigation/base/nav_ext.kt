package com.solopov.corenavigation.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solopov.corenavigation.BaseRoute
import com.solopov.corenavigation.BaseRoute.Route
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val ARG_KEY = "arg"

inline fun <reified ARG> ARG.toQuery() = ARG_KEY + "=" + Json.encodeToString(this)

fun makeRoute(route: Route) = route.route

fun NavGraphBuilder.composable(route: Route, content: @Composable (NavBackStackEntry) -> Unit) =
    composable(
        route = makeRoute(route),
        content = content,
    )

fun makeRouteWithArg(route: BaseRoute.RouteWithArg) = "${route.route}?$ARG_KEY={$ARG_KEY}"
inline fun <reified ARG> makeRouteWithArg(route: BaseRoute.RouteWithArg, arg: ARG) =
    "${route.route}?${arg.toQuery()}"

fun createBaseRoute(route: BaseRoute): String {
    return when (route) {
        is Route -> makeRoute(route)
        is BaseRoute.RouteWithArg -> makeRouteWithArg(route)
    }
}
