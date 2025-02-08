package com.solopov.coreuicompose.uikit

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.solopov.coreuicompose.R
import com.solopov.coreuitheme.compose.AppTheme
import com.solopov.coreuitheme.compose.Dimen
import com.solopov.coreuitheme.compose.Gray80

@Composable
fun StyloButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    @DrawableRes leadingIconRes: Int? = null,
    paddingValues: PaddingValues = PaddingValues(vertical = Dimen.padding_medium),
    enabled: Boolean = true,
    containerColor: Color = AppTheme.colorScheme.primary,
    contentColor: Color = AppTheme.colorScheme.onPrimary,
    shape: CornerBasedShape = AppTheme.shapes.small,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        contentPadding = paddingValues,
        onClick = onClick,
        shape = shape,
        enabled = enabled,
    ) {
        ButtonContent(text = text, leadingIconRes = leadingIconRes)
    }
}

@Composable
fun OutlinedStyloButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    @DrawableRes leadingIconRes: Int? = null,
    paddingValues: PaddingValues = PaddingValues(vertical = Dimen.padding_medium),
    enabled: Boolean = true,
    borderColor: Color = Gray80,
    borderWidth: Dp = Dimen.border_stroke_width_medium,
    backgroundColor: Color = AppTheme.colorScheme.onPrimary,
    shape: CornerBasedShape = AppTheme.shapes.small,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier
            .background(backgroundColor),
        border = BorderStroke(borderWidth, borderColor),
        contentPadding = paddingValues,
        onClick = onClick,
        shape = shape,
        enabled = enabled,
    ) {
        ButtonContent(text = text, leadingIconRes = leadingIconRes)
    }

}

@Composable
fun ButtonContent(
    text: String? = null,
    @DrawableRes leadingIconRes: Int? = null,
) {
    val iconPaddingEnd = when {
        text.isNullOrBlank() -> Dimen.padding_no
        else -> Dimen.padding_extra_small
    }

    leadingIconRes?.let {
        Icon(
            painter = painterResource(id = it),
            contentDescription = stringResource(id = R.string.default_btn_icon_content_description),
            modifier = Modifier.padding(end = iconPaddingEnd),
        )
    }

    text?.let {
        Text(
            text = it,
            style = AppTheme.typography.bodyLarge, color = LocalContentColor.current
        )
    }
}
