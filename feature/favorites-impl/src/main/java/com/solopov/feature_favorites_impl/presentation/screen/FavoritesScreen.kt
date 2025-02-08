package com.solopov.feature_favorites_impl.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.solopov.coreuicompose.uikit.StyloSnackbar
import com.solopov.coreuicompose.uikit.StyloTopAppBar
import com.solopov.coreuitheme.compose.AppTheme
import com.solopov.coreuitheme.compose.Dimen
import com.solopov.coreuitheme.compose.Gray50
import com.solopov.feature_favorites_api.domain.model.FavoriteClothing
import com.solopov.feature_favorites_impl.R
import com.solopov.feature_favorites_impl.presentation.model.FavoritesIntent
import com.solopov.feature_favorites_impl.presentation.model.FavoritesViewState
import com.solopov.feature_favorites_impl.presentation.viewmodel.FavoritesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {

    val scaffoldState = rememberScaffoldState()

    val viewModel = hiltViewModel<FavoritesViewModel>()

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            StyloTopAppBar(
                title = stringResource(R.string.favorites_title),
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
            onRemoveFromFavorites = { id ->
                viewModel.onIntent(FavoritesIntent.RemoveFromFavorites(id))
            }
        )
    }

    LaunchedEffect(key1 = null) {
        viewModel.onIntent(FavoritesIntent.LoadAllFavorites)
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    viewState: FavoritesViewState,
    paddingValues: PaddingValues,
    onRemoveFromFavorites: (Long) -> Unit,
) {

    when (viewState) {
        is FavoritesViewState.Data -> {
            if (viewState.favoritesList.isEmpty()) {
                EmptyFavoritesView()
            } else {
                LazyVerticalGrid(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = Dimen.padding_24),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(Dimen.padding_20),
                    verticalArrangement = Arrangement.spacedBy(Dimen.padding_16)
                ) {
                    items(viewState.favoritesList, key = { it.id }) { item ->
                        FavoriteItem(
                            item = item,
                            onRemoveFromFavorites = {
                                onRemoveFromFavorites.invoke(item.id)
                            }
                        )
                    }
                }
            }
        }

        FavoritesViewState.Initial -> {}
        FavoritesViewState.Loading -> {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun EmptyFavoritesView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimen.padding_80),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_empty_favorites_heart),
            contentDescription = null
        )
        Spacer(Modifier.height(Dimen.padding_16))

        Text(
            text = stringResource(R.string.no_favorites_message),
            style = AppTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(Dimen.padding_16))

        Text(
            text = stringResource(R.string.add_favorites_via_home_message),
            style = AppTheme.typography.bodyLarge,
            color = Gray50,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FavoriteItem(
    modifier: Modifier = Modifier,
    item: FavoriteClothing,
    onRemoveFromFavorites: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimen.padding_8),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
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
                    .clickable { onRemoveFromFavorites() }
                    .align(Alignment.TopEnd)
                    .padding(Dimen.padding_4),
                painter = painterResource(
                    id = com.solopov.coreuicompose.R.drawable.ic_remove_from_favs
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

