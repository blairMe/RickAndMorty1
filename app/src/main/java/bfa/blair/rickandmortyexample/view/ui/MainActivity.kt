package bfa.blair.rickandmortyexample.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bfa.blair.rickandmortyexample.view.viewmodel.MainViewModel
import bfa.blair.rickandmortyexample.databinding.ActivityMainBinding
import bfa.blair.rickandmortyexample.model.network.Character
import bfa.blair.rickandmortyexample.view.adapter.MainAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.characterLiveData.observe(this) { state ->
            processCharactersResponse(state)
        }
    }

    private fun processCharactersResponse(state : ScreenState<List<Character>?>) {
        when(state) {
            is ScreenState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                binding.progressBar.visibility = View.GONE
                if (state.data != null) {
                    val adapter = MainAdapter(state.data)
                    binding.charactersRV.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding.charactersRV.adapter = adapter
                }
            }
            is ScreenState.Error -> {
                binding.progressBar.visibility = View.GONE
                Snackbar.make(binding.progressBar.rootView, state.message!!, Snackbar.LENGTH_SHORT).show()

            }
        }
    }
}