package com.example.suki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.suki.ui.theme.SukiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SukiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Mostrar HomeScreen en vez de Greeting
                    com.example.suki.ui.home.HomeScreen()
                }
            }
        }
    }
}