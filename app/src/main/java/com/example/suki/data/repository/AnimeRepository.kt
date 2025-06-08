package com.example.suki.data.repository

import com.example.suki.data.model.Anime

interface AnimeRepository {
    suspend fun getAnimes(query: String = ""): List<Anime>
}
