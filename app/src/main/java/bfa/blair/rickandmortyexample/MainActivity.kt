package bfa.blair.rickandmortyexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bfa.blair.rickandmortyexample.databinding.ActivityMainBinding
import bfa.blair.rickandmortyexample.network.ApiClient
import bfa.blair.rickandmortyexample.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.characterLiveData.observe(this, { characters ->
            val adapter = MainAdapter(characters)
            binding.charactersRV.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.charactersRV.adapter = adapter
        })



//        val client = ApiClient.apiService.fetchCharacters("1")
//        client.enqueue(object : retrofit2.Callback<CharacterResponse> {
//            override fun onResponse(
//                call: Call<CharacterResponse>,
//                response: Response<CharacterResponse>,
//            ) {
//                if(response.isSuccessful) {
//                    // Log.d("Characters", ""+response.body())
//                    val result = response.body()?.result
//                    result?.let {
//                        val adapter = MainAdapter(result)
//                        binding.charactersRV.layoutManager =
//                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//                        binding.charactersRV.adapter = adapter
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
//                Log.d("Characters Failed", ""+t.message)
//            }
//
//        })
    }
}