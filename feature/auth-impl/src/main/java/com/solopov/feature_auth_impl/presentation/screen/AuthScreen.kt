package com.solopov.feature_auth_impl.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solopov.coreuitheme.compose.AppTheme
import com.solopov.coreuitheme.compose.Dimen
import com.solopov.feature_auth_impl.R
import com.solopov.feature_auth_impl.presentation.model.AuthAction
import com.solopov.feature_auth_impl.presentation.model.AuthIntent
import com.solopov.feature_auth_impl.presentation.model.AuthViewState
import com.solopov.feature_auth_impl.presentation.viewmodel.AuthViewModel
import com.solopov.feature_auth_impl.presentation.viewmodel.AuthViewModel.TextFieldType

private const val AUTH_SWITCH_LINK_TAG = "auth_switch_link"

@Composable
fun AuthScreen(
) {
    val viewModel = hiltViewModel<AuthViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val viewAction by viewModel.viewAction.collectAsStateWithLifecycle(null)

    AuthScreen(
        viewState = viewState,
        onAuthSwitchClick = {
            viewModel.onIntent(AuthIntent.AuthSwitch)
        },
        onFieldValueChange = { fieldType, newValue ->
            viewModel.onIntent(AuthIntent.InputText(fieldType, newValue))
        },
        onAuthClick = {
            viewModel.onIntent(AuthIntent.Authenticate)
        }
    )

    LaunchedEffect(viewAction) {
        when (viewAction) {
            AuthAction.ToHomeScreen -> {}
            null -> {}
        }
    }
}

@Composable
private fun AuthScreen(
    viewState: AuthViewState,
    onAuthSwitchClick: () -> Unit,
    onFieldValueChange: (TextFieldType, String) -> Unit,
    onAuthClick: () -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(
                top = Dimen.padding_small,
                start = Dimen.padding_large,
                end = Dimen.padding_large
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = stringResource(id = viewState.titleRes),
            style = AppTheme.typography.headlineMedium,
            color = AppTheme.colorScheme.primary,
        )

        Text(
            text = stringResource(id = viewState.subtitleRes),
            style = AppTheme.typography.bodyLarge,
            color = com.solopov.coreuitheme.compose.Gray50,
        )

        Spacer(modifier = Modifier.height(Dimen.height_extra_large))

        when (viewState) {
            is AuthViewState.SignUp -> {
                com.solopov.coreuicompose.uikit.StyloTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewState.name,
                    label = stringResource(R.string.name_label),
                    placeholder = stringResource(R.string.name_placeholder),
                    maxLines = 1,
                    onValueChange = { newValue ->
                        onFieldValueChange(TextFieldType.NAME, newValue)
                    }
                )
                Spacer(modifier = Modifier.height(Dimen.height_medium))
            }

            else -> {}
        }

        com.solopov.coreuicompose.uikit.StyloTextField(
            modifier = Modifier.fillMaxWidth(),
            text = viewState.email,
            label = stringResource(R.string.email_label),
            placeholder = stringResource(R.string.email_placeholder),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            maxLines = 1,
            onValueChange = { newValue ->
                onFieldValueChange(TextFieldType.EMAIL, newValue)
            },

            )
        Spacer(modifier = Modifier.height(Dimen.height_medium))

        com.solopov.coreuicompose.uikit.StyloTextField(
            modifier = Modifier.fillMaxWidth(),
            text = viewState.password,
            label = stringResource(R.string.password_label),
            placeholder = stringResource(R.string.password_placeholder),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image =
                    if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility

                val description =
                    if (isPasswordVisible) stringResource(R.string.hide_password_content_description) else stringResource(
                        R.string.show_password_content_description
                    )

                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(imageVector = image, description)
                }
            },
            maxLines = 1,
            onValueChange = { newValue ->
                onFieldValueChange(TextFieldType.PASSWORD, newValue)
            },
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
        )

        Spacer(modifier = Modifier.height(Dimen.height_medium))

        Text(
            text = when (viewState) {
                is AuthViewState.SignUp -> {
                    buildAnnotatedString {
                        append(stringResource(id = R.string.sign_up_agreement_plain_text))

                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.W800
                            )
                        ) {
                            append(stringResource(id = R.string.terms_link_text))
                        }

                        append(stringResource(R.string.and_text))

                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.W800
                            )
                        ) {
                            append(stringResource(id = R.string.privacy_policy_link_text))
                        }

                    }
                }

                else -> {
                    buildAnnotatedString {
                        append(stringResource(id = R.string.reset_password_plain_text))

                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.W800
                            )
                        ) {
                            append(stringResource(id = R.string.reset_password_link_text))
                        }
                    }
                }
            },
            style = AppTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(Dimen.height_extra_large))

        com.solopov.coreuicompose.uikit.StyloButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = viewState.authBtnTextRes)
        ) {
            onAuthClick.invoke()
        }

        Spacer(modifier = Modifier.height(Dimen.height_extra_large))

        DividerWithText(text = stringResource(R.string.or_divider_text))

        Spacer(modifier = Modifier.height(Dimen.height_extra_large))


        com.solopov.coreuicompose.uikit.OutlinedStyloButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = viewState.googleAuthBtnTextRes),
            leadingIconRes = R.drawable.ic_google,
            backgroundColor = AppTheme.colorScheme.onPrimary,
        ) {}

        Spacer(modifier = Modifier.height(Dimen.height_48))

        val annotatedAuthSwitchText = buildAnnotatedString {
            append(stringResource(id = viewState.authSwitchLinkDescriptionRes))
            append(" ")

            withStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.W800
                )
            ) {
                pushStringAnnotation(
                    tag = AUTH_SWITCH_LINK_TAG,
                    annotation = stringResource(id = viewState.authSwitchLinkTextRes)
                )
                append(stringResource(id = viewState.authSwitchLinkTextRes))
                pop()
            }
        }

        ClickableText(
            text = annotatedAuthSwitchText,
            style = AppTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) { offset ->
            annotatedAuthSwitchText.getStringAnnotations(
                tag = AUTH_SWITCH_LINK_TAG,
                start = offset,
                end = offset
            )
                .firstOrNull()?.let {
                    onAuthSwitchClick.invoke()
                }
        }

    }
}

@Composable
fun DividerWithText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(
            color = AppTheme.colorScheme.primaryContainer,
            thickness = 1.dp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = text,
            style = AppTheme.typography.bodyMedium,
            color = com.solopov.coreuitheme.compose.Gray50,
            modifier = Modifier.padding(horizontal = Dimen.padding_extra_small)
        )

        HorizontalDivider(
            color = AppTheme.colorScheme.primaryContainer,
            thickness = 1.dp,
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
@PreviewLightDark
private fun AuthScreenPreview() {
//    AuthScreen(
//        viewState = AuthViewState.LogIn(),
//        onAuthSwitch = { }) { _, _ ->
//    }
}
