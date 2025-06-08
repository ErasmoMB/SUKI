package com.example.suki.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suki.data.model.AnimeDetail
import com.example.suki.data.model.Character
import com.example.suki.data.model.Episode
import com.example.suki.data.model.Recommendation
import com.example.suki.data.repository.AnimeRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel(private val repository: AnimeRepositoryImpl = AnimeRepositoryImpl()) : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeDetail?>(null)
    val animeDetail: StateFlow<AnimeDetail?> = _animeDetail

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _episodes = MutableStateFlow<List<Episode>>(emptyList())
    val episodes: StateFlow<List<Episode>> = _episodes

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations

    fun fetchAnimeDetail(id: Int) {
        viewModelScope.launch {
            _animeDetail.value = repository.getAnimeDetail(id)
        }
    }

    fun fetchCharacters(id: Int) {
        viewModelScope.launch {
            _characters.value = repository.getCharacters(id)
        }
    }

    fun fetchEpisodes(id: Int) {
        viewModelScope.launch {
            _episodes.value = repository.getEpisodes(id)
        }
    }

    fun fetchRecommendations(id: Int) {
        viewModelScope.launch {
            _recommendations.value = repository.getRecommendations(id)
        }
    }
}
