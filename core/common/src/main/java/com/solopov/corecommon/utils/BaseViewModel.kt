package com.solopov.corecommon.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Intent, Action>(
    initialState: State
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    protected val _action = MutableSharedFlow<Action>(replay = 0)

    protected var uiState: State
        get() = _uiState.value
        set(value) {
            _uiState.value = value
        }

    protected var action: Action?
        get() = if (_action.replayCache.isNotEmpty()) {
            _action.replayCache.last()
        } else {
            null
        }
        set(value) {
            viewModelScope.launch {
                if (value != null) {
                    _action.emit(value)
                } else {
                    _action.resetReplayCache()
                }
            }
        }

    val viewState: StateFlow<State>
        get() = _uiState.asStateFlow()
    val viewAction: Flow<Action>
        get() = _action.asSharedFlow()

    abstract fun onIntent(intent: Intent)
}
