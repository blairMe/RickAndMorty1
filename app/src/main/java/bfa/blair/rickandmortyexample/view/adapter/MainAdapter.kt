package bfa.blair.rickandmortyexample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bfa.blair.rickandmortyexample.databinding.RvCharactersBinding
import coil.load
import coil.transform.CircleCropTransformation
import bfa.blair.rickandmortyexample.model.network.Character

class MainAdapter(private val characterList : List<Character>)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val binding: RvCharactersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(character : Character) {
            val name = binding.characterName
            val image = binding.characterImage

            name.text = character.name
            image.load(character.image) {
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(RvCharactersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}