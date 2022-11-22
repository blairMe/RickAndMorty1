package bfa.blair.rickandmortyexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bfa.blair.rickandmortyexample.network.ApiClient
import bfa.blair.rickandmortyexample.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = ApiClient.apiService.fetchCharacters("1")
        client.enqueue(object : retrofit2.Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>,
            ) {
                if(response.isSuccessful) {
                    Log.d("Characters", ""+response.body())
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.d("Characters Failed", ""+t.message)
            }

        })
    }
}