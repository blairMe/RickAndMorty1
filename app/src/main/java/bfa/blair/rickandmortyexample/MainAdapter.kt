package bfa.blair.rickandmortyexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bfa.blair.rickandmortyexample.databinding.RvCharactersBinding
import coil.load
import coil.transform.CircleCropTransformation
import bfa.blair.rickandmortyexample.network.Character

class MainAdapter(val characterList : List<Character>)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    // private lateinit var binding : RvCharactersBinding

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

//    inner class MainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bindData(character : Character) {
//            var name = itemView.findViewById<TextView>(R.id.characterName)
//            var image = itemView.findViewById<ImageView>(R.id.characterImage)
//
//            name.text = character.name
//            image.load(character.image) {
//                transformations(CircleCropTransformation())
//            }
//        }
//    }
}