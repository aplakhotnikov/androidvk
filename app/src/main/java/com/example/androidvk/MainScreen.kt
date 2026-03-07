package com.example.androidvk

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidvk.ui.theme.AndroidvkTheme

@Composable
fun MainScreen() {
    val context = LocalContext.current;
    var inputText by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText,
            onValueChange = {inputText = it},
            label = { Text(stringResource(R.string.hint_text)) },
            singleLine = true
        );

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            openInfoActivity(context, inputText);
        }) {
            Text(stringResource(R.string.open_button_text));
        }
    }
}

private fun openInfoActivity(context: Context, text: String) {
    if (!text.isNotBlank()) {
        showError(context, context.getString(R.string.empty_text_error));

        return;
    }

    val intent = Intent(context, InfoScreenActivity::class.java).apply{
        putExtra("TEXT", text);
    }

    context.startActivity(intent);
}

private fun showError(context: Context, text: String) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()
}

@Preview()
@Composable()
private fun Preview() {
    AndroidvkTheme() {
        Surface() {
            MainScreen();
        }
    }
}