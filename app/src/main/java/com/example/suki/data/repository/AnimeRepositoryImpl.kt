package com.example.suki.data.repository

import com.example.suki.data.model.Anime
import com.example.suki.data.model.AnimeDetail
import com.example.suki.data.model.Character
import com.example.suki.data.model.Episode
import com.example.suki.data.model.Recommendation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class AnimeRepositoryImpl : AnimeRepository {
    override suspend fun getAnimes(query: String): List<Anime> = withContext(Dispatchers.IO) {
        // Ejemplo usando Jikan API (MyAnimeList)
        val url = if (query.isBlank())
            "https://api.jikan.moe/v4/anime"
        else
            "https://api.jikan.moe/v4/anime?q=$query"
        val response = URL(url).readText()
        val json = JSONObject(response)
        val data = json.getJSONArray("data")
        List(data.length()) { i ->
            val item = data.getJSONObject(i)
            Anime(
                id = item.getInt("mal_id"),
                title = item.getString("title"),
                imageUrl = item.getJSONObject("images").getJSONObject("jpg").getString("image_url")
            )
        }
    }

    suspend fun getAnimeDetail(id: Int): AnimeDetail = withContext(Dispatchers.IO) {
        val url = "https://api.jikan.moe/v4/anime/$id"
        val response = URL(url).readText()
        val json = JSONObject(response).getJSONObject("data")
        AnimeDetail(
            title = json.getString("title"),
            score = json.optDouble("score").toFloat().takeIf { !json.isNull("score") },
            episodes = json.optInt("episodes"),
            synopsis = json.getString("synopsis"),
            imageUrl = json.getJSONObject("images").getJSONObject("jpg").getString("image_url"),
            status = json.optString("status"),
            genres = json.optJSONArray("genres")?.let { arr ->
                List(arr.length()) { i -> arr.getJSONObject(i).getString("name") }
            } ?: emptyList(),
            year = json.optInt("year").takeIf { !json.isNull("year") },
            type = json.optString("type").takeIf { it.isNotBlank() },
            aired = json.optJSONObject("aired")?.let { airedObj ->
                val from = airedObj.optString("from")?.takeIf { it.isNotBlank() }
                val to = airedObj.optString("to")?.takeIf { it.isNotBlank() }
                if (from != null && to != null && from != to) "$from a $to" else from ?: to
            },
            duration = json.optString("duration").takeIf { it.isNotBlank() },
            rating = json.optString("rating").takeIf { it.isNotBlank() }
        )
    }

    suspend fun getCharacters(id: Int): List<Character> = withContext(Dispatchers.IO) {
        val url = "https://api.jikan.moe/v4/anime/$id/characters"
        try {
            val response = URL(url).readText()
            val json = JSONObject(response)
            val data = json.getJSONArray("data")
            List(data.length()) { i ->
                val item = data.getJSONObject(i)
                Character(
                    id = item.getInt("mal_id"),
                    name = item.getString("name"),
                    imageUrl = item.getJSONObject("images").getJSONObject("jpg").getString("image_url")
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getEpisodes(id: Int): List<Episode> = withContext(Dispatchers.IO) {
        val url = "https://api.jikan.moe/v4/anime/$id/episodes"
        try {
            val response = URL(url).readText()
            val json = JSONObject(response)
            val data = json.getJSONArray("data")
            List(data.length()) { i ->
                val item = data.getJSONObject(i)
                Episode(
                    id = item.getInt("mal_id"),
                    title = item.getString("title"),
                    aired = item.optString("aired")
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getRecommendations(id: Int): List<Recommendation> = withContext(Dispatchers.IO) {
        val url = "https://api.jikan.moe/v4/anime/$id/recommendations"
        try {
            val response = URL(url).readText()
            val json = JSONObject(response)
            val data = json.getJSONArray("data")
            List(data.length()) { i ->
                val item = data.getJSONObject(i)
                Recommendation(
                    id = item.getInt("mal_id"),
                    title = item.getString("title"),
                    imageUrl = item.getJSONObject("images").getJSONObject("jpg").getString("image_url")
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
