package com.example.androidvk.presentation.applist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.androidvk.domain.AppDetails
import com.example.androidvk.ui.theme.AndroidvkTheme

@Composable
fun AppListItem(app: AppDetails, onItemClick: (AppDetails) -> Unit, onLogoClick: (AppDetails) -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable{onItemClick(app)},
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically) {

        AsyncImage(
            model = app.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable{onLogoClick(app)},
        );

        Column() {
            Text(text = app.name, fontSize = 16.sp, fontWeight = FontWeight.Bold,)
            Text(text = app.description, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
            Text(text = app.category, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        thickness = DividerDefaults.Thickness,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    )
}

@Preview
@Composable
private fun Preview() {
    AndroidvkTheme() {
        Surface() {
            AppListItem(
                app = AppDetails(
                    ID = "1",
                    name = "Имя",
                    description = "Описание",
                    category = "Развлечения",
                    iconUrl = "",
                ),
                onItemClick = {}
            );
        }
    }
}