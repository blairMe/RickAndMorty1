package bfa.blair.rickandmortyexample.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

object ApiClient {
    // https://rickandmortyapi.com/api/character/?page=1
    private val baseUrl = "https://rickandmortyapi.com"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}