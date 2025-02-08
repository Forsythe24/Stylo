package com.solopov.coreuicompose.uikit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.solopov.coreuicompose.tools.getBottomInsetWithBottomNavigation
import com.solopov.coreuitheme.compose.AppTheme
import com.solopov.coreuitheme.compose.Dimen

@Composable
fun StyloSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    StyloSnackbarHost(
        snackbarHostState = snackbarHostState,
        modifier = modifier
            .padding(getBottomInsetWithBottomNavigation())
            .padding(Dimen.padding_8),
        containerColor = AppTheme.colorScheme.primary
    )
}

@Composable
private fun StyloSnackbarHost(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    containerColor: Color
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier,
        snackbar = {
            StyloSnackbar(it.message, containerColor)
        },
    )
}

@Composable
private fun StyloSnackbar(message: String, containerColor: Color) {
    Snackbar(
        containerColor = containerColor,
        shape = RoundedCornerShape(Dimen.radius_extra_small)
    ) {
        Text(
            text = message,
            color = AppTheme.colorScheme.onPrimaryContainer,
            style = AppTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
