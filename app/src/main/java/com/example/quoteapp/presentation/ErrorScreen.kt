package com.example.quoteapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(error: String) {
    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = error,
            modifier = Modifier
                .padding(horizontal = 16.dp))
    }
}