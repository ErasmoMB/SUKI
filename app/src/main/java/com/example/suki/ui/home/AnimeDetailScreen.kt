package com.example.suki.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.suki.data.model.AnimeDetail
import com.example.suki.data.model.Character
import com.example.suki.data.model.Episode
import com.example.suki.data.model.Recommendation
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi

@OptIn(ExperimentalLayoutApi::class)
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF23213A))
            .padding(16.dp)
    ) {
        animeDetail?.let { detail ->
            // Cabecera con imagen grande y datos principales
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.Top
            ) {
                AsyncImage(
                    model = detail.imageUrl,
                    contentDescription = detail.title,
                    modifier = Modifier
                        .height(180.dp)
                        .width(130.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = detail.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                    detail.score?.let {
                        Text(
                            text = "★ $it",
                            color = Color(0xFFFFD700),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "${detail.type ?: ""}  ${detail.year ?: ""}", color = Color.LightGray)
                    Text(text = "Estado: ${detail.status}", color = Color.White)
                    Text(text = "Capítulos: ${detail.episodes ?: "N/A"}", color = Color.White)
                    detail.duration?.let {
                        Text(text = "Duración: $it", color = Color.White)
                    }
                    detail.rating?.let {
                        Text(text = "Rating: $it", color = Color.White)
                    }
                    detail.aired?.let {
                        Text(text = "Emitido: $it", color = Color.White)
                    }
                }
            }
            // Géneros reales
            if (detail.genres.isNotEmpty()) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    detail.genres.forEach { tag ->
                        AssistChip(
                            onClick = {},
                            label = { Text(tag, color = Color.White) },
                            colors = AssistChipDefaults.assistChipColors(labelColor = Color.White)
                        )
                    }
                }
            }
            Text(
                text = "Sinopsis",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2842))
            ) {
                val synopsis = detail.synopsis
                val cleanedSynopsis = synopsis.replace(Regex("(?i)written by mal r[\\w ]*"), "").trim()
                if (cleanedSynopsis.isEmpty()) {
                    Text(
                        text = "Sinopsis no disponible.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.padding(12.dp)
                    )
                } else {
                    Text(
                        text = cleanedSynopsis,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(
            text = "Capítulos",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        if (episodes.isEmpty()) {
            Text(
                text = "No hay episodios disponibles.",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f, fill = false)) {
                items(episodes) { episode ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2842))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "+ ${episode.title}",
                                color = Color.White,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = episode.aired ?: "",
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}
