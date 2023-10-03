package com.rhezarijaya.veggiez.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rhezarijaya.veggiez.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center),
        ) {
            Image(
                contentDescription = null,
                painter = painterResource(id = R.drawable.me),
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                style = MaterialTheme.typography.displaySmall,
                text = stringResource(R.string.my_name),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = stringResource(R.string.my_email),
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun AboutScreenPreview(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        Surface {
            AboutScreen()
        }
    }
}