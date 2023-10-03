package com.rhezarijaya.veggiez.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rhezarijaya.veggiez.R

@Composable
fun FavoriteFloatingActionButton(
    isFavorite: Boolean,
    vegetableId: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    FloatingActionButton(
        onClick = { onClick(vegetableId) },
        modifier = modifier,
    ) {
        if (!isFavorite) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = stringResource(R.string.save_on_favorite)
            )
        } else {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = stringResource(R.string.remove_from_favorite)
            )
        }
    }
}