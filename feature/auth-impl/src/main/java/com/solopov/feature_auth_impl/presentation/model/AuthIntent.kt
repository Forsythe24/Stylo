package com.solopov.feature_auth_impl.presentation.model

import com.solopov.feature_auth_impl.presentation.viewmodel.AuthViewModel

sealed interface AuthIntent {
    data class InputText(val fieldType: AuthViewModel.TextFieldType, val newValue: String): AuthIntent
    data object Authenticate: AuthIntent
    data object AuthSwitch: AuthIntent
}
