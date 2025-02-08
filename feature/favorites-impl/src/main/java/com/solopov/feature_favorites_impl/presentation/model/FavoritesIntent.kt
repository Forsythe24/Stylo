package com.solopov.feature_favorites_impl.presentation.model


sealed interface FavoritesIntent {
    data class RemoveFromFavorites(val id: Long) : FavoritesIntent
    data object LoadAllFavorites : FavoritesIntent
}
