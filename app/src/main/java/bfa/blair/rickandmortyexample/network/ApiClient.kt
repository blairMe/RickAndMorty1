package bfa.blair.rickandmortyexample.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {
    // https://rickandmortyapi.com/api/character/?page=1
    private val BASE_URL = "https://rickandmortyapi.com/api/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}

// Api Service
interface ApiService {
    @GET("character")
    fun fetchCharacters(@Query("page") page : String) : Call<CharacterResponse>
}