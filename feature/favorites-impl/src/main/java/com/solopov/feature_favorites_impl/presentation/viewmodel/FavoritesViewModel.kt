package com.solopov.feature_favorites_impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.solopov.corecommon.utils.BaseViewModel
import com.solopov.feature_favorites_api.domain.RemoveFromFavoritesUseCase
import com.solopov.feature_favorites_api.domain.GetAllFavoritesUseCase
import com.solopov.feature_favorites_impl.presentation.model.FavoritesAction
import com.solopov.feature_favorites_impl.presentation.model.FavoritesIntent
import com.solopov.feature_favorites_impl.presentation.model.FavoritesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
): BaseViewModel<FavoritesViewState, FavoritesIntent, FavoritesAction>(
    initialState = FavoritesViewState.Initial
) {

    override fun onIntent(intent: FavoritesIntent) {
        when (intent) {
            is FavoritesIntent.RemoveFromFavorites -> removeFromFavorites(intent.id)
            FavoritesIntent.LoadAllFavorites -> loadFavorites()
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            uiState = FavoritesViewState.Loading
            kotlin.runCatching {
                getAllFavoritesUseCase.invoke()
            }.onSuccess {
                uiState = FavoritesViewState.Data(it)
            }
        }
    }

    private fun removeFromFavorites(clothingId: Long) {
        viewModelScope.launch {
            val currentFavorites = (uiState as FavoritesViewState.Data).favoritesList.toMutableList()
            uiState = FavoritesViewState.Loading
            kotlin.runCatching {
                removeFromFavoritesUseCase.invoke(clothingId)
            }.onSuccess {
                currentFavorites.removeIf { it.id == clothingId }
                uiState = FavoritesViewState.Data(currentFavorites)
            }
        }
    }
}
