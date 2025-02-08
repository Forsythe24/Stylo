package com.solopov.feature_auth_impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.solopov.corecommon.utils.BaseViewModel
import com.solopov.feature_auth_api.domain.usecase.LogInUseCase
import com.solopov.feature_auth_impl.presentation.LoadState
import com.solopov.feature_auth_impl.presentation.model.AuthAction
import com.solopov.feature_auth_impl.presentation.model.AuthIntent
import com.solopov.feature_auth_impl.presentation.model.AuthViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val logInUseCase: LogInUseCase
) : BaseViewModel<AuthViewState, AuthIntent, AuthAction>(
    initialState = AuthViewState.LogIn()
) {

    override fun onIntent(intent: AuthIntent) {
        with (intent) {
            when(this) {
                is AuthIntent.InputText -> onFieldValueChange(fieldType, newValue)
                AuthIntent.AuthSwitch -> onAuthSwitch()
                AuthIntent.Authenticate -> onAuthenticate()
            }
        }
    }

    private fun onAuthenticate() {
        viewModelScope.launch {
            uiState = updateLoadState(LoadState.LOADING)
            kotlin.runCatching {
                logInUseCase.invoke()
            }.onSuccess {
                uiState = updateLoadState(LoadState.SUCCESS)
            }.onFailure {

            }
        }
    }

    private fun updateLoadState(newLoadState: LoadState): AuthViewState {
        return when (val currentState = uiState) {
            is AuthViewState.SignUp -> currentState.copy(loadState = newLoadState)
            is AuthViewState.LogIn -> currentState.copy(loadState = newLoadState)
        }
    }

    private fun onAuthSwitch() {
        uiState = when (uiState) {
            is AuthViewState.SignUp -> AuthViewState.LogIn()
            is AuthViewState.LogIn -> AuthViewState.SignUp()
        }
    }

    private fun onFieldValueChange(fieldType: TextFieldType, newValue: String) {
        uiState = when (fieldType) {
            TextFieldType.EMAIL -> {
                when (uiState) {
                    is AuthViewState.SignUp -> (uiState as AuthViewState.SignUp).copy(email = newValue)
                    is AuthViewState.LogIn -> (uiState as AuthViewState.LogIn).copy(email = newValue)
                }
            }

            TextFieldType.PASSWORD -> {
                when (uiState) {
                    is AuthViewState.SignUp -> (uiState as AuthViewState.SignUp).copy(password = newValue)
                    is AuthViewState.LogIn -> (uiState as AuthViewState.LogIn).copy(password = newValue)
                }
            }

            TextFieldType.NAME -> {
                (uiState as? AuthViewState.SignUp)?.copy(name = newValue) ?: uiState
            }
        }

    }

    enum class TextFieldType {
        EMAIL,
        PASSWORD,
        NAME
    }
}
