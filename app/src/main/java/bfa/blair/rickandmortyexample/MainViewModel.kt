package bfa.blair.rickandmortyexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bfa.blair.rickandmortyexample.network.ApiClient
import bfa.blair.rickandmortyexample.network.CharacterResponse
import bfa.blair.rickandmortyexample.network.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository
                        = Repository(ApiClient.apiService)) : ViewModel() {
    private var _charactersLiveData = MutableLiveData<List<Character>>()
    val characterLiveData : LiveData<List<Character>>

    get() = _charactersLiveData

    init {
        fetchCharacter()
    }

    private fun fetchCharacter() {
        val client = repository.getCharacters("1")
        client.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>,
            ) {
                _charactersLiveData.postValue(response.body()?.result)
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }

        })
    }
}