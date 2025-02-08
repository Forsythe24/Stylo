package com.solopov.coreuicompose.uikit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.solopov.coreuitheme.IconAction
import com.solopov.coreuitheme.compose.AppTheme
import com.solopov.coreuitheme.compose.Dimen
import kotlin.math.max
import kotlin.math.roundToInt

private typealias OnBackIconClick = () -> Boolean

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyloTopAppBar(
    title: String?,
    titleTextStyle: TextStyle = AppTheme.typography.headlineMedium,
    navigationIcon: Pair<Int, OnBackIconClick>? = null,
    actions: Map<Int, IconAction> = emptyMap(),
    contentPadding: PaddingValues = PaddingValues(),
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior,
    rightMenu: @Composable () -> Unit = {}
) {

    val offsetLimit =
        with(LocalDensity.current) { -StyloTopAppBarConfig.ContainerHeight.toPx() / 4 }
    SideEffect {
        if (scrollBehavior.state.heightOffsetLimit != offsetLimit) {
            scrollBehavior.state.heightOffsetLimit = offsetLimit
        }
    }


    val navigationIconUi = @Composable {
        if (navigationIcon != null) {
            Icon(
                painter = painterResource(navigationIcon.first),
                tint = AppTheme.colorScheme.primary,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = Dimen.padding_8, end = Dimen.padding_large)
                    .clip(CircleShape)
                    .clickable(onClick = { navigationIcon.second() })
                    .padding(Dimen.padding_8)
            )
        }
    }

    val actionsUi = @Composable {
        Row {
            actions.forEach { action ->
                Icon(
                    painter = painterResource(action.key),
                    contentDescription = action.value.contentDescription,
                    tint = colors.actionIconContentColor,
                    modifier = Modifier
                        .padding(end = Dimen.padding_8)
                        .clip(CircleShape)
                        .clickable(enabled = true, onClick = action.value.onClick)
                        .padding(Dimen.padding_8),
                )
            }
            rightMenu()
        }
    }

    Surface(color = AppTheme.colorScheme.background) {
        val height = StyloTopAppBarConfig.ContainerHeight
        val heightPx = LocalDensity.current.run { height.toPx() }
        TopAppBarLayout(
            modifier = Modifier.padding(contentPadding),
            heightPx = heightPx,

            navigationIconContentColor = colors.navigationIconContentColor,
            titleContentColor = colors.titleContentColor,
            actionIconContentColor = colors.actionIconContentColor,

            title = title,
            titleTextStyle = titleTextStyle,
            titleAlpha = 1f,
            titleVerticalArrangement = Arrangement.Center,
            titleHorizontalArrangement = Arrangement.Start,
            titleBottomPadding = 0,
            hideTitleSemantics = false,
            navigationIcon = navigationIconUi,
            actions = actionsUi,
        )
    }
}

@Composable
private fun TopAppBarLayout(
    modifier: Modifier,
    heightPx: Float,
    navigationIconContentColor: Color,
    titleContentColor: Color,
    actionIconContentColor: Color,
    title: String?,
    titleTextStyle: TextStyle,
    titleAlpha: Float,
    titleVerticalArrangement: Arrangement.Vertical,
    titleHorizontalArrangement: Arrangement.Horizontal,
    titleBottomPadding: Int,
    hideTitleSemantics: Boolean,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable () -> Unit,
) {
    Layout(
        {
            Box(
                Modifier
                    .layoutId("navigationIcon")
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides navigationIconContentColor,
                    content = navigationIcon
                )
            }
            Box(
                Modifier
                    .layoutId("title")
                    .then(if (hideTitleSemantics) Modifier.clearAndSetSemantics { } else Modifier)
            ) {
                title?.let { Text(text = it, style = titleTextStyle) }
            }
            Box(
                Modifier
                    .layoutId("actionIcons")
                    .padding(end = Dimen.padding_4)
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides actionIconContentColor,
                    content = actions
                )
            }
        },
        modifier = modifier
    ) { measurables, constraints ->
        val navigationIconPlaceable =
            measurables.first { it.layoutId == "navigationIcon" }.measure(constraints)
        val actionIconsPlaceable =
            measurables.first { it.layoutId == "actionIcons" }.measure(constraints)

        val maxTitleWidth =
            constraints.maxWidth - navigationIconPlaceable.width - actionIconsPlaceable.width
        val titlePlaceable =
            measurables
                .first { it.layoutId == "title" }
                .measure(constraints.copy(maxWidth = maxTitleWidth))
        val titleBaseline =
            if (titlePlaceable[LastBaseline] != AlignmentLine.Unspecified) {
                titlePlaceable[LastBaseline]
            } else {
                0
            }

        val layoutHeight = heightPx.roundToInt()

        layout(constraints.maxWidth, layoutHeight) {
            // Navigation icon
            navigationIconPlaceable.placeRelative(
                x = 0,
                y = (layoutHeight - navigationIconPlaceable.height) / 2
            )

            // Title
            titlePlaceable.placeRelative(
                x = when (titleHorizontalArrangement) {
                    Arrangement.Center -> (constraints.maxWidth - titlePlaceable.width) / 2
                    Arrangement.End ->
                        constraints.maxWidth - titlePlaceable.width - actionIconsPlaceable.width
                    else -> max(12.dp.roundToPx(), navigationIconPlaceable.width)
                },
                y = when (titleVerticalArrangement) {
                    Arrangement.Center -> (layoutHeight - titlePlaceable.height) / 2
                    Arrangement.Bottom ->
                        if (titleBottomPadding == 0) layoutHeight - titlePlaceable.height
                        else layoutHeight - titlePlaceable.height - max(
                            0,
                            titleBottomPadding - titlePlaceable.height + titleBaseline
                        )
                    else -> 0
                }
            )

            actionIconsPlaceable.placeRelative(
                x = constraints.maxWidth - actionIconsPlaceable.width,
                y = (layoutHeight - actionIconsPlaceable.height) / 2
            )
        }
    }
}

private object StyloTopAppBarConfig {
    val ContainerHeight = 64.dp
}
