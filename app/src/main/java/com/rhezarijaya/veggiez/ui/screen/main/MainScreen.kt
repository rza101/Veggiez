package com.rhezarijaya.veggiez.ui.screen.main

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rhezarijaya.veggiez.data.model.Vegetable
import com.rhezarijaya.veggiez.ui.ViewModelFactory
import com.rhezarijaya.veggiez.ui.component.NoItemText
import com.rhezarijaya.veggiez.ui.component.SearchBarComponent
import com.rhezarijaya.veggiez.ui.component.VegetableItem

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    ),
    onItemClick: (String) -> Unit = {},
) {
    val vegetablesData by mainViewModel.vegetables.collectAsState()
    val query by mainViewModel.searchQuery

    MainContent(
        vegetablesData = vegetablesData,
        onItemClick = onItemClick,
        onQueryChange = mainViewModel::search,
        searchQuery = query,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(
    vegetablesData: List<Vegetable>,
    onItemClick: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    searchQuery: String,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 0.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .testTag("vegetable_list"),
    ) {
        stickyHeader {
            SearchBarComponent(
                onQueryChange = onQueryChange,
                query = searchQuery,
            )
        }

        if (vegetablesData.isEmpty()) {
            item {
                NoItemText(
                    modifier = Modifier.fillMaxSize()
                )
            }
        } else {
            items(vegetablesData, key = { it.id }) { vegetable ->
                VegetableItem(
                    id = vegetable.id,
                    name = vegetable.name,
                    photoUrl = vegetable.photoUrl,
                    onItemClick = { id ->
                        onItemClick(id)
                    },
                    modifier = Modifier
                        .animateItemPlacement(tween(durationMillis = 100))
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun MainScreenPreview(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        Surface {
            MainScreen()
        }
    }
}