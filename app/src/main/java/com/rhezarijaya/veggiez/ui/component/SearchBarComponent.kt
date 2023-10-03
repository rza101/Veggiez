package com.rhezarijaya.veggiez.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rhezarijaya.veggiez.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SearchBar(
        active = false,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        onActiveChange = {},
        onQueryChange = onQueryChange,
        onSearch = {},
        placeholder = {
            Text(stringResource(R.string.search))
        },
        query = query,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp),
    ) {

    }
}