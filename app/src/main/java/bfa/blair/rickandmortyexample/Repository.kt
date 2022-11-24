package bfa.blair.rickandmortyexample

import bfa.blair.rickandmortyexample.network.ApiService

class Repository(val apiService : ApiService) {
    fun getCharacters(page : String) = apiService.fetchCharacters(page)
}