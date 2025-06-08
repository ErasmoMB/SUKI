package com.example.suki.data.model

// Nuevo modelo extendido para ficha completa

data class AnimeDetail(
    val title: String,
    val score: Float?,
    val episodes: Int?,
    val synopsis: String,
    val imageUrl: String?,
    val status: String,
    val genres: List<String> = emptyList(),
    val year: Int? = null,
    val type: String? = null,
    val aired: String? = null,
    val duration: String? = null,
    val rating: String? = null
) : java.io.Serializable
