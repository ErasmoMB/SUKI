package com.example.suki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.suki.ui.home.AnimeDetailScreen
import com.example.suki.ui.home.HomeScreen
import com.example.suki.ui.theme.SukiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SukiTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(onAnimeClick = { animeId ->
                                navController.navigate("detail/$animeId")
                            })
                        }
                        composable("detail/{animeId}") { backStackEntry ->
                            val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull() ?: 0
                            AnimeDetailScreen(animeId = animeId)
                        }
                    }
                }
            }
        }
    }
}