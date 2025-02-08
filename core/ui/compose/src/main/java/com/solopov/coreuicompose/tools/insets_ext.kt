package com.solopov.coreuicompose.tools

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import com.solopov.coreuicompose.uikit.BottomNavigationDefaults

@Composable
fun getTopInset() = WindowInsets.statusBars.asPaddingValues()

@Composable
fun getBottomInset() = WindowInsets.navigationBars
    .asPaddingValues()

@Composable
fun getBottomInsetWithIme() = WindowInsets.navigationBars
    .union(WindowInsets.ime)
    .asPaddingValues()

@Composable
fun getBottomInsetWithBottomNavigation() = WindowInsets.navigationBars
    .add(WindowInsets(bottom = BottomNavigationDefaults.Height))
    .union(WindowInsets.ime)
    .asPaddingValues()
