package com.solopov.stylo.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solopov.stylo.domain.GetTokenUseCase
import com.solopov.stylo.model.AppAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {
    private var _initialScreenAction = MutableStateFlow<AppAction?>(null)
    val initialScreenAction: StateFlow<AppAction?> = _initialScreenAction

    init {
        viewModelScope.launch {
            getTokenUseCase.invoke()
                .collectLatest {
                    if (it.isEmpty()) {
                        _initialScreenAction.update { AppAction.OpenAuthScreen }
                    } else {
                        _initialScreenAction.update { AppAction.OpenAppScreen }
                    }
                }
        }
    }
}
