package com.flipkart.machinecoding.worldt2.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.flipkart.machinecoding.worldt2.domain.model.Team

@Composable
fun TeamCard(
    team: Team,
    selected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = if (selected) 2.dp else 0.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = team.flagUrl,
                contentDescription = "${team.name} Flag",
                modifier = Modifier
                    .size(60.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Fit,
                placeholder = coil.compose.rememberAsyncImagePainter("https://via.placeholder.com/60"),
                error = coil.compose.rememberAsyncImagePainter("https://via.placeholder.com/60/FF0000"),
                onError = { state ->
                    val url = team.flagUrl
                    if (url.isNullOrBlank()) {
                        android.util.Log.e("CoilError", "URL is empty or null for team: ${team.name}")
                    } else {
                        android.util.Log.e("CoilError", "Failed to load URL: $url", state.result.throwable)
                    }
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = team.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}