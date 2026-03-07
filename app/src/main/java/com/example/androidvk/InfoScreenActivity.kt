package com.example.androidvk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.androidvk.ui.theme.AndroidvkTheme

class InfoScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidvkTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val text = intent.getStringExtra("TEXT") ?: "";

                    InfoScreen(text, onBack = ::onBack);
                }
            }
        }
    }

    private fun onBack() {
        finish();
    }
}