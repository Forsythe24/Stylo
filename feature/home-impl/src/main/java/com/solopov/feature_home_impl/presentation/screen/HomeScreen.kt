package com.solopov.feature_home_impl.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.solopov.coreuicompose.uikit.StyloSnackbar
import com.solopov.coreuicompose.uikit.StyloTopAppBar
import com.solopov.coreuitheme.compose.AppTheme
import com.solopov.coreuitheme.compose.Dimen
import com.solopov.feature_home_api.domain.model.Clothing
import com.solopov.feature_home_impl.presentation.model.HomeIntent
import com.solopov.feature_home_impl.presentation.model.HomeViewState
import com.solopov.feature_home_impl.presentation.viewmodel.HomeViewModel
import com.solopov.feauture_home_impl.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import com.solopov.coreuicompose.R as coreUiComposeR


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scaffoldState = rememberScaffoldState()

    val viewModel = hiltViewModel<HomeViewModel>()

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            StyloTopAppBar(
                title = stringResource(R.string.home_screen_title),
                scrollBehavior = pinnedScrollBehavior(),
                contentPadding = PaddingValues(start = Dimen.padding_12),
                actions = mapOf(),
            )
        },
        snackbarHost = {
            StyloSnackbar(snackbarHostState = scaffoldState.snackbarHostState)
        },
    ) { paddingValues ->
        Content(
            viewState = viewState,
            paddingValues = paddingValues,
            onClothingInFavoritesStateChange = { id, newState ->
                viewModel.onIntent(
                    HomeIntent.ChangeClothingInFavoritesState(
                        id = id,
                        newState = newState
                    )
                )
            }
        )
    }

    LaunchedEffect(key1 = null) {
        viewModel.onIntent(HomeIntent.LoadAllClothing)
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    viewState: HomeViewState,
    paddingValues: PaddingValues,
    onClothingInFavoritesStateChange: (Long, Boolean) -> Unit,
) {

    when (viewState) {
        is HomeViewState.Data -> {
            LazyVerticalGrid(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = Dimen.padding_24),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(Dimen.padding_20),
                verticalArrangement = Arrangement.spacedBy(Dimen.padding_20)
            ) {
                items(viewState.clothingList, key = { it.id }) { item ->
                    ClothingItem(
                        item = item,
                        onInFavoritesChangeClicked = {
                            onClothingInFavoritesStateChange.invoke(item.id, !item.isFavorite)
                        }
                    )
                }
            }
        }

        HomeViewState.Initial -> {}
        HomeViewState.Loading -> {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ClothingItem(
    modifier: Modifier = Modifier,
    item: Clothing,
    onInFavoritesChangeClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimen.padding_8),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)
                .clip(AppTheme.shapes.small) // Ensure clipping is here
                .background(Color.Gray) // Optional: Debug background
        ) {
            AsyncImage(
                model = item.photoUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Icon(
                modifier = Modifier
                    .clickable { onInFavoritesChangeClicked() }
                    .align(Alignment.TopEnd)
                    .padding(Dimen.padding_4),
                painter = painterResource(
                    id = if (item.isFavorite) coreUiComposeR.drawable.ic_remove_from_favs
                    else coreUiComposeR.drawable.ic_add_to_favs
                ),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = item.name,
            style = AppTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Text(
            text = stringResource(com.solopov.corecommon.R.string.price_in_rubles_template, item.price),
            style = AppTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}
