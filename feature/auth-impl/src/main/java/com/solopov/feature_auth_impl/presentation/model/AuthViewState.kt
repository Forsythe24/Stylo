package com.solopov.feature_auth_impl.presentation.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.solopov.feature_auth_impl.R
import com.solopov.feature_auth_impl.presentation.LoadState

sealed interface AuthViewState {
    @get:StringRes
    val titleRes: Int

    @get:StringRes
    val subtitleRes: Int

    @get:StringRes
    val authBtnTextRes: Int

    val onAuthBtnClick: () -> Unit

    @get:StringRes
    val googleAuthBtnTextRes: Int

    val onGoogleAuthBtnClick: () -> Unit

    @get:StringRes
    val authSwitchLinkDescriptionRes: Int

    @get:StringRes
    val authSwitchLinkTextRes: Int

    val onAuthSwitchLinkTextClick: () -> Unit

    val loadState: LoadState

    val email: String

    val password: String


    @Immutable
    data class LogIn(
        override val titleRes: Int = R.string.log_in_title,
        override val subtitleRes: Int = R.string.log_in_subtitle,
        override val authBtnTextRes: Int = R.string.log_in_btn_text,
        override val onAuthBtnClick: () -> Unit = {},
        override val googleAuthBtnTextRes: Int = R.string.google_log_in_btn_text,
        override val onGoogleAuthBtnClick: () -> Unit = {},
        override val authSwitchLinkDescriptionRes: Int = R.string.sign_up_link_description,
        override val authSwitchLinkTextRes: Int = R.string.sign_up_link_text,
        override val onAuthSwitchLinkTextClick: () -> Unit = {},
        override val loadState: LoadState = LoadState.LOADING,
        override val email: String = "",
        override val password: String = "",
    ) : AuthViewState

    @Immutable
    data class SignUp(
        override val titleRes: Int = R.string.sign_up_title,
        override val subtitleRes: Int = R.string.sign_up_subtitle,
        override val authBtnTextRes: Int = R.string.sign_up_btn_text,
        override val onAuthBtnClick: () -> Unit = {},
        override val googleAuthBtnTextRes: Int = R.string.google_sign_up_btn_text,
        override val onGoogleAuthBtnClick: () -> Unit = {},
        override val authSwitchLinkDescriptionRes: Int = R.string.log_in_link_description,
        override val authSwitchLinkTextRes: Int = R.string.log_in_link_text,
        override val onAuthSwitchLinkTextClick: () -> Unit = {},
        override val loadState: LoadState = LoadState.LOADING,
        val name: String = "",
        override val email: String = "",
        override val password: String = "",
        ) : AuthViewState
}

