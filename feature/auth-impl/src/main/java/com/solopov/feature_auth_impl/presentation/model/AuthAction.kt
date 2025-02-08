package com.solopov.feature_auth_impl.presentation.model

sealed class AuthAction {
    data object ToHomeScreen: AuthAction()
}
