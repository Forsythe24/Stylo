package com.solopov.feature_home_impl.presentation.model


sealed interface HomeIntent {
    data class ChangeClothingInFavoritesState(val id: Long, val newState: Boolean): HomeIntent
    data object LoadAllClothing : HomeIntent
}
