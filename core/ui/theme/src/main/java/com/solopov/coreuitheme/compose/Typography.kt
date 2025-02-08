package com.solopov.coreuitheme.compose

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

internal val LocalTypography = staticCompositionLocalOf { DefaultTypography }
internal val DefaultTypography = Typography(
    headlineLarge = TextStyle(
        fontSize = 64.sp,
        letterSpacing = (-0.05).em,
        fontFamily = FontFamilyW600,
        lineHeight = 52.sp,
    ),
    headlineMedium = TextStyle(
        fontSize = 32.sp,
        letterSpacing = (-0.05).em,
        fontFamily = FontFamilyW600,
        lineHeight = 32.sp,
    ),
    headlineSmall = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamilyW600,
        lineHeight = 28.sp,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamilyW400,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamilyW400,
        lineHeight = 20.sp,
    ),

    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamilyW400,
        lineHeight = 16.sp,
    ),
//    body1 = TextStyle(
//        fontSize = 16.sp,
//        letterSpacing = 0.03.em,
//        fontFamily = FontFamilyW400,
//        lineHeight = 24.sp,
//    ),
//    body1Medium = TextStyle(
//        fontSize = 16.sp,
//        letterSpacing = 0.02.em,
//        fontFamily = FontFamilyW500,
//        lineHeight = 24.sp,
//    ),
//    body1SemiBold = TextStyle(
//        fontSize = 16.sp,
//        fontFamily = FontFamilyW600,
//        lineHeight = 24.sp,
//    ),
//    body2 = TextStyle(
//        fontSize = 14.sp,
//        fontFamily = FontFamilyW400,
//        lineHeight = 20.sp,
//    ),
//    body2Medium = TextStyle(
//        fontSize = 14.sp,
//        fontFamily = FontFamilyW500,
//        lineHeight = 20.sp,
//    ),
//    body2SemiBold = TextStyle(
//        fontSize = 14.sp,
//        fontFamily = FontFamilyW600,
//        lineHeight = 20.sp,
//    ),
//    body3 = TextStyle(
//        fontSize = 12.sp,
//        fontFamily = FontFamilyW400,
//        lineHeight = 16.sp,
//    ),
//    body3Medium = TextStyle(
//        fontSize = 12.sp,
//        fontFamily = FontFamilyW500,
//        lineHeight = 16.sp,
//    ),
//    body3SemiBold = TextStyle(
//        fontSize = 12.sp,
//        fontFamily = FontFamilyW600,
//        lineHeight = 16.sp,
//    ),
)
