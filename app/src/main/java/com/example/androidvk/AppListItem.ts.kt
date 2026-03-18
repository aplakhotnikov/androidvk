package com.example.androidvk

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidvk.ui.theme.AndroidvkTheme

@Composable
fun AppListItem(app: AppItem, onItemClick: (Int) -> Unit) {
     Row(
        modifier = Modifier.fillMaxWidth().clickable{onItemClick(app.ID)},
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically) {

        AppIcon(app);

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

@Composable
private fun AppIcon(app: AppItem) {
    when (app.name.lowercase()) {
        else -> Icon(
            imageVector = Icons.Default.Face,
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AndroidvkTheme() {
        Surface() {
            AppListItem(
                app = AppItem(
                    ID = 1,
                    name = "Имя",
                    description = "Описание",
                    category = "Категория",
                ),
                {}
            );
        }
    }
}