package com.solopov.coreuitheme.compose

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.solopov.coruitheme.R

internal val FontFamilyW400 = FontFamily(
    listOf(
        Font(R.font.general_sans_regular, weight = FontWeight.W400, style = FontStyle.Normal),
    )
)
internal val FontFamilyW500 = FontFamily(
    listOf(
        Font(R.font.general_sans_medium, weight = FontWeight.W500, style = FontStyle.Normal),
    )
)
internal val FontFamilyW600 = FontFamily(
    listOf(
        Font(R.font.general_sans_semibold, weight = FontWeight.W600, style = FontStyle.Normal),
    )
)
