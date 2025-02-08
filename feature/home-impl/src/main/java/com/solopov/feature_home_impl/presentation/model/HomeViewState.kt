package com.solopov.feature_home_impl.presentation.model

import androidx.compose.runtime.Immutable
import com.solopov.feature_home_api.domain.model.Clothing

sealed interface HomeViewState {
    data object Initial : HomeViewState

    @Immutable
    data class Data(val clothingList: List<Clothing>) : HomeViewState

    data object Loading : HomeViewState
}

