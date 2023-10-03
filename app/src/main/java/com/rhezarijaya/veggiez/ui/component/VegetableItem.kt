package com.rhezarijaya.veggiez.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun VegetableItem(
    id: String,
    name: String,
    photoUrl: String?,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        onClick = {
            onItemClick(id)
        },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row {
            AsyncImage(
                contentDescription = null,
                contentScale = ContentScale.Crop,
                model = photoUrl,
                modifier = Modifier
                    .height(150.dp)
                    .weight(0.5f)
                    .clip(RoundedCornerShape(4.dp)),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                text = name,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.4f),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun VegetableItemPreview() {
    MaterialTheme {
        VegetableItem(
            id = "1",
            name = "Asparagus",
            photoUrl = "https://vivregourmet.com/wp-content/uploads/2020/07/Asparagus.png"
        )
    }
}
