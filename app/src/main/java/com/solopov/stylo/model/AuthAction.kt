package com.solopov.stylo.model

sealed interface AppAction {
    data object OpenAuthScreen : AppAction
    data object OpenAppScreen : AppAction
}
