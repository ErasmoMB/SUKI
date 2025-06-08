package com.example.suki.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suki.data.model.Anime
import com.example.suki.data.repository.AnimeRepository
import com.example.suki.data.repository.AnimeRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AnimeRepository = AnimeRepositoryImpl()
) : ViewModel() {
    private val _animes = MutableStateFlow<List<Anime>>(emptyList())
    val animes: StateFlow<List<Anime>> = _animes

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    init {
        fetchAnimes()
    }

    fun fetchAnimes(query: String = "") {
        _query.value = query
        viewModelScope.launch {
            _animes.value = repository.getAnimes(query)
        }
    }
}
