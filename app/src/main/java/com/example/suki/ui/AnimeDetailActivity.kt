package com.example.suki.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.suki.R
import com.example.suki.data.model.AnimeDetail

class AnimeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("AnimeDetailActivity", "onCreate called")
        setContentView(R.layout.activity_anime_detail)

        val intent = intent
        Log.d("AnimeDetailActivity", "Intent: $intent, extras: ${intent.extras}")
        var anime = intent.getSerializableExtra("anime_detail") as? AnimeDetail
        Log.d("AnimeDetailActivity", "AnimeDetail recibido: $anime")

        if (anime == null) {
            // Modo debug: datos de ejemplo si no se recibe el intent
            anime = AnimeDetail(
                title = "Ejemplo de Anime",
                score = 8.7f,
                episodes = 24,
                synopsis = "Esta es una sinopsis de ejemplo para depuración.",
                imageUrl = "https://i.imgur.com/2M7Hasn.jpg",
                status = "En emisión",
                genres = listOf("Acción", "Aventura"),
                year = 2022,
                type = "TV",
                aired = "2022-01-01 a 2022-06-01",
                duration = "24 min por ep",
                rating = "PG-13"
            )
            Log.w("AnimeDetailActivity", "No se recibió AnimeDetail por intent. Usando datos de ejemplo para debug.")
        }

        val imageView = findViewById<ImageView>(R.id.imageViewAnime)
        val titleView = findViewById<TextView>(R.id.textViewTitle)
        val scoreView = findViewById<TextView>(R.id.textViewScore)
        val episodesView = findViewById<TextView>(R.id.textViewEpisodes)
        val synopsisView = findViewById<TextView>(R.id.textViewSynopsis)

        titleView.text = anime.title
        scoreView.text = "Puntaje: ${anime.score ?: "N/A"}"
        episodesView.text = "Capítulos: ${anime.episodes ?: "N/A"}"
        synopsisView.text = anime.synopsis
        Log.d("AnimeDetailActivity", "UI poblada con datos del anime")
        if (!anime.imageUrl.isNullOrEmpty()) {
            Glide.with(this).load(anime.imageUrl).into(imageView)
            Log.d("AnimeDetailActivity", "Imagen cargada desde: ${anime.imageUrl}")
        } else {
            Log.d("AnimeDetailActivity", "No hay URL de imagen para cargar")
        }
    }
}
