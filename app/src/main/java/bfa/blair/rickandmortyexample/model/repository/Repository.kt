package bfa.blair.rickandmortyexample.model.repository

import bfa.blair.rickandmortyexample.model.network.ApiService

class Repository(private val apiService : ApiService) {
    suspend fun getCharacters(page : String) = apiService.fetchCharacters(page)
}