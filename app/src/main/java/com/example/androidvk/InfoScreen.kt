package com.example.androidvk

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidvk.ui.theme.AndroidvkTheme

@Composable
fun InfoScreen(text: String, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text(stringResource(R.string.back_button_text))
        }
    }
}

@Preview()
@Composable()
private fun Preview() {
    AndroidvkTheme() {
        Surface() {
            InfoScreen("текст превью", {});
        }
    }
}