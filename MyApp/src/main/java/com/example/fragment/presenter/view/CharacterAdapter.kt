package com.example.fragment.presenter.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragment.R
import com.example.fragment.databinding.CharacterViewForResviewBinding
import com.example.fragment.domain.entities.Character
import com.example.fragment.presenter.viewModel.MainActivityViewModel


class CharacterAdapter(
    viewModel: MainActivityViewModel
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    init{
        viewModel.getCharacterList{
            character = it
        }

    }
    var character : List<Character> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class CharacterViewHolder(
        val binding: CharacterViewForResviewBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CharacterViewForResviewBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        with(holder.binding){
                Name.text = character[position].name
                location.text = character[position].location["name"]
                Glide.with(this.imagePlace.context)
                    .load(character[position].image)
                    .circleCrop()
                    .placeholder(R.drawable.ic_baseline_accessibility_24)
                    .error(R.drawable.ic_baseline_accessibility_24)
                    .into(imagePlace)

        }
    }

    override fun getItemCount() = character.size

}