package com.rhezarijaya.veggiez.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rhezarijaya.veggiez.ui.ViewModelFactory

@Composable
fun DetailScreen(
    vegetableId: String,
    detailViewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    ),
) {
    val vegetable = detailViewModel.getVegetableById(vegetableId)

    vegetable?.let { data ->
        DetailContent(
            name = data.name,
            description = data.description,
            photoUrl = data.photoUrl,
        )
    }
}

@Composable
fun DetailContent(
    name: String,
    description: String,
    photoUrl: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            contentDescription = name,
            contentScale = ContentScale.FillWidth,
            model = photoUrl,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            text = name,
            modifier = Modifier
                .padding(horizontal = 16.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = description,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 80.dp),
        )
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun DetailScreenPreview(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        Surface {
            DetailScreen("1")
        }
    }
}