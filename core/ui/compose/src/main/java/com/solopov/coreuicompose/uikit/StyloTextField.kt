package com.solopov.coreuicompose.uikit

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.solopov.coreuitheme.compose.AppTheme

@Composable
fun StyloTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    maxLines: Int = Int.MAX_VALUE,
    text: String,
    label: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Text(text = label, style = AppTheme.typography.bodyLarge)
    OutlinedTextField(
        modifier = modifier,
        value = text,
        textStyle = AppTheme.typography.bodyLarge,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        shape = AppTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = AppTheme.colorScheme.primaryContainer),
        placeholder = {
            Text(text = placeholder, style = AppTheme.typography.bodyLarge)
        },
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation
    )
}
