package com.solopov.feature_auth_impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.solopov.corenavigation.AppRoute
import com.solopov.corenavigation.AuthFeatureProvider
import com.solopov.corenavigation.base.composable
import com.solopov.feature_auth_impl.presentation.screen.AuthScreen
import javax.inject.Inject

class AuthFeatureProviderImpl @Inject constructor() : AuthFeatureProvider {

    override fun NavGraphBuilder.authGraph(controller: NavHostController) {
        composable(AppRoute.Auth) {
            AuthScreen(
            )
        }
    }
}
