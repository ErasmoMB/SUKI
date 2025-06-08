package com.example.suki.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.suki.data.model.AnimeDetail
import com.example.suki.data.model.Character
import com.example.suki.data.model.Episode
import com.example.suki.data.model.Recommendation

@Composable
fun AnimeDetailScreen(animeId: Int, viewModel: AnimeDetailViewModel = viewModel()) {
    val animeDetail by viewModel.animeDetail.collectAsState()
    val characters by viewModel.characters.collectAsState()
    val episodes by viewModel.episodes.collectAsState()
    val recommendations by viewModel.recommendations.collectAsState()

    viewModel.fetchAnimeDetail(animeId)
    viewModel.fetchCharacters(animeId)
    viewModel.fetchEpisodes(animeId)
    viewModel.fetchRecommendations(animeId)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        animeDetail?.let { detail ->
            Text(text = detail.title, style = MaterialTheme.typography.titleLarge, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(model = detail.imageUrl, contentDescription = detail.title, modifier = Modifier.height(200.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = detail.synopsis, style = MaterialTheme.typography.bodyMedium, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Characters", style = MaterialTheme.typography.titleMedium, color = Color.White)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(characters) { character ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                    AsyncImage(model = character.imageUrl, contentDescription = character.name, modifier = Modifier.size(50.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = character.name, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Episodes", style = MaterialTheme.typography.titleMedium, color = Color.White)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(episodes) { episode ->
                Text(text = "${episode.title} - Aired: ${episode.aired ?: "Unknown"}", color = Color.White, modifier = Modifier.padding(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Recommendations", style = MaterialTheme.typography.titleMedium, color = Color.White)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(recommendations) { recommendation ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                    AsyncImage(model = recommendation.imageUrl, contentDescription = recommendation.title, modifier = Modifier.size(50.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = recommendation.title, color = Color.White)
                }
            }
        }
    }
}
