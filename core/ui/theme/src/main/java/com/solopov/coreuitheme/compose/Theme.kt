package com.solopov.coreuitheme.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColorScheme = staticCompositionLocalOf { LightColorScheme }

private val LightColorScheme = lightColorScheme(
    primary = Gray10,
    onPrimary = Gray100,
    primaryContainer = Gray90,
    onPrimaryContainer = Gray60,

    secondary = Color(0xFFB0B9D1),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFC3B6B2),
    onSecondaryContainer = Color(0xFF2B1D19),

    tertiary = Color(0xFFF5F6FA),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFF4F3D1),
    onTertiaryContainer = Color(0xFF312911),

    error = Red50,
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),

    outline = Color(0xFFCECCCC),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFF5F6FA),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFEEEEEE),
    onSurfaceVariant = Color(0xFF000000),
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = DefaultTypography,
        shapes = AppShapes,
        content = content,
    )
}

internal val LocalLightTheme = staticCompositionLocalOf { false }

object AppTheme {

    val isLightTheme: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalLightTheme.current

    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}
