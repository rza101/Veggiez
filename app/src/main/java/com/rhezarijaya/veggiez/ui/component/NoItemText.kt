package com.rhezarijaya.veggiez.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.rhezarijaya.veggiez.R

@Composable
fun NoItemText(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.no_item),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun NoItemTextPreview() {
    MaterialTheme {
        NoItemText()
    }
}