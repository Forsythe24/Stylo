package com.solopov.corenavigation.base

import androidx.navigation.NavHostController

abstract class Navigator(protected val navHostController: NavHostController) {
    fun back() = navHostController.popBackStack()
}
