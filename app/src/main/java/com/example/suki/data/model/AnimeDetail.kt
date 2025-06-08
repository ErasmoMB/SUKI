package com.example.suki.data.model

data class AnimeDetail(
    val id: Int,
    val title: String,
    val synopsis: String,
    val imageUrl: String? = null,
    val score: Double? = null,
    val episodes: Int? = null
)
