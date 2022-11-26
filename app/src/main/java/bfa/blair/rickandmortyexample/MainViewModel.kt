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
    private var _charactersLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val characterLiveData : LiveData<ScreenState<List<Character>?>>

    get() = _charactersLiveData

    init {
        fetchCharacter()
    }

    private fun fetchCharacter() {
        val client = repository.getCharacters("1")
        _charactersLiveData.postValue(ScreenState.Loading(null))
        client.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>,
            ) {
                if(response.isSuccessful){
                    _charactersLiveData.postValue(ScreenState.Success(response.body()?.result))
                } else {
                    _charactersLiveData.postValue(ScreenState.Error(response.code().toString(), null))
                }

            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                // Log.d("Failure", t.message.toString())
                _charactersLiveData.postValue(ScreenState.Error(t.message.toString(), null))
            }

        })
    }
}