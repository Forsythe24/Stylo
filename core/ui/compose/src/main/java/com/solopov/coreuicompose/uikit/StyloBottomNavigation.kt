package com.solopov.coreuicompose.uikit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.solopov.coreuitheme.compose.AppTheme
import com.solopov.coreuitheme.compose.Dimen

val LocalBottomNavigationHeight = staticCompositionLocalOf { BOTTOM_BAR_HEIGHT }
val BOTTOM_BAR_HEIGHT = 56.dp
val BOTTOM_BAR_HEIGHT_GONE = 0.dp

@Composable
fun StyloBottomNavigation(
    backgroundColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = .9f),
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    elevation: Dp = BottomNavigationDefaults.Elevation,
    contentPadding: PaddingValues = PaddingValues(),
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        shadowElevation = elevation,
    ) {
        HorizontalDivider(
            color = AppTheme.colorScheme.primaryContainer,
            thickness = Dimen.border_stroke_width_medium
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(BottomNavigationDefaults.Height + contentPadding.calculateTopPadding() + contentPadding.calculateBottomPadding())
                .padding(contentPadding)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

object BottomNavigationDefaults {
    val Elevation = 0.dp
    var Height = BOTTOM_BAR_HEIGHT_GONE
}
