package com.rhezarijaya.veggiez.ui.screen.favorite

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rhezarijaya.veggiez.data.model.Vegetable
import com.rhezarijaya.veggiez.ui.ViewModelFactory
import com.rhezarijaya.veggiez.ui.component.NoItemText
import com.rhezarijaya.veggiez.ui.component.VegetableItem

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    ),
    onItemClick: (String) -> Unit = {},
) {
    val favoriteData by favoriteViewModel.favorites.collectAsState()
    FavoriteContent(favoriteData, onItemClick)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteContent(
    favoriteData: List<Vegetable>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (favoriteData.isEmpty()) {
        NoItemText(
            modifier = modifier.fillMaxSize()
        )
    } else {
        LazyColumn(
            contentPadding = PaddingValues(top = 0.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier,
        ) {
            items(favoriteData, key = { it.id }) { vegetable ->
                VegetableItem(
                    id = vegetable.id,
                    name = vegetable.name,
                    photoUrl = vegetable.photoUrl,
                    onItemClick = { id ->
                        onItemClick(id)
                    },
                    modifier = Modifier
                        .animateItemPlacement(tween(durationMillis = 100)),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun FavoriteScreenPreview(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        Surface {
            FavoriteScreen()
        }
    }
}