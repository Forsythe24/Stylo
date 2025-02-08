package com.solopov.feature_cart_impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.solopov.corenavigation.AppRoute
import com.solopov.corenavigation.CartFeatureProvider
import com.solopov.corenavigation.base.composable
import com.solopov.feature_cart_impl.presentation.screen.CartScreen
import javax.inject.Inject

class CartFeatureProviderImpl @Inject constructor() : CartFeatureProvider {

    override fun NavGraphBuilder.cartGraph(controller: NavHostController) {
        composable(AppRoute.MainBottomBar.Cart) { CartScreen() }
    }
}
