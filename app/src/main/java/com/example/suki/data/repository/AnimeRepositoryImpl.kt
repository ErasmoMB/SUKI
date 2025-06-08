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
            id = json.getInt("mal_id"),
            title = json.getString("title"),
            synopsis = json.getString("synopsis"),
            imageUrl = json.getJSONObject("images").getJSONObject("jpg").getString("image_url"),
            score = json.optDouble("score"),
            episodes = json.optInt("episodes")
        )
    }

    suspend fun getCharacters(id: Int): List<Character> = withContext(Dispatchers.IO) {
        val url = "https://api.jikan.moe/v4/anime/$id/characters"
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
    }

    suspend fun getEpisodes(id: Int): List<Episode> = withContext(Dispatchers.IO) {
        val url = "https://api.jikan.moe/v4/anime/$id/episodes"
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
    }

    suspend fun getRecommendations(id: Int): List<Recommendation> = withContext(Dispatchers.IO) {
        val url = "https://api.jikan.moe/v4/anime/$id/recommendations"
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
    }
}
