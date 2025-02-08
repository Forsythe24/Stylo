package com.solopov.feature_favorites_impl.presentation.model

import androidx.compose.runtime.Immutable
import com.solopov.feature_favorites_api.domain.model.FavoriteClothing

sealed interface FavoritesViewState {
    data object Initial : FavoritesViewState

    @Immutable
    data class Data(val favoritesList: List<FavoriteClothing>) : FavoritesViewState

    data object Loading : FavoritesViewState
}

