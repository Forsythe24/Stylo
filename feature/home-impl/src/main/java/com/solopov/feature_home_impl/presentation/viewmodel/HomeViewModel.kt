package com.solopov.feature_home_impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.solopov.corecommon.utils.BaseViewModel
import com.solopov.feature_home_api.domain.usecase.ChangeClothingInFavoritesStateUseCase
import com.solopov.feature_home_api.domain.usecase.GetAllClothingUseCase
import com.solopov.feature_home_impl.presentation.model.HomeAction
import com.solopov.feature_home_impl.presentation.model.HomeIntent
import com.solopov.feature_home_impl.presentation.model.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllClothingUseCase: GetAllClothingUseCase,
    private val changeClothingInFavoritesStateUseCase: ChangeClothingInFavoritesStateUseCase
) : BaseViewModel<HomeViewState, HomeIntent, HomeAction>(
    initialState = HomeViewState.Initial
) {

    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.ChangeClothingInFavoritesState -> changeClothingInFavoritesState(
                clothingId = intent.id,
                newState = intent.newState
            )
            HomeIntent.LoadAllClothing -> loadAllClothing()
        }
    }

    private fun loadAllClothing() {
        viewModelScope.launch {
            uiState = HomeViewState.Loading
            kotlin.runCatching {
                getAllClothingUseCase.invoke()
            }.onSuccess {
                uiState = HomeViewState.Data(it)
            }
        }
    }

    private fun changeClothingInFavoritesState(clothingId: Long, newState: Boolean) {
        viewModelScope.launch {
            val currentClothingList = (uiState as? HomeViewState.Data)?.clothingList?.toMutableList()
            uiState = HomeViewState.Loading
            kotlin.runCatching {
                changeClothingInFavoritesStateUseCase.invoke(clothingId, newState)
            }.onSuccess {
                currentClothingList?.replaceAll {
                    if (it.id == clothingId)
                        it.copy(isFavorite = newState)
                    else it
                }
                if (currentClothingList != null) {
                    uiState = HomeViewState.Data(currentClothingList)
                } else {
                    loadAllClothing()
                }
            }
        }
    }
}
