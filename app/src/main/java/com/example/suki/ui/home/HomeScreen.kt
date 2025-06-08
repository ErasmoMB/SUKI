package com.example.suki.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.suki.data.model.Anime

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val animes by viewModel.animes.collectAsState()
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF18182C))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO: abrir menú */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
            }
            TextField(
                value = search,
                onValueChange = {
                    search = it
                    viewModel.fetchAnimes(it)
                },
                placeholder = { Text("Buscar....", color = Color.LightGray) },
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFF23234A)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF23234A),
                    unfocusedContainerColor = Color(0xFF23234A),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(animes) { anime ->
                AnimeCard(anime)
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(0.7f),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF23234A))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (anime.imageUrl != null) {
                AsyncImage(
                    model = anime.imageUrl,
                    contentDescription = anime.title,
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(anime.title, color = Color.White)
        }
    }
}
