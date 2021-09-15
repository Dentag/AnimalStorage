package com.dentag.animalstorage.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dentag.animalstorage.databinding.ItemAnimalBinding
import com.dentag.animalstorage.room.Animal

class AnimalAdapter(private val animalClickListener: AnimalClickListener) :
    ListAdapter<Animal, AnimalAdapter.AnimalViewHolder>(animalDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimalBinding.inflate(inflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AnimalViewHolder(private val binding: ItemAnimalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: Animal) {
            binding.nameTextView.text = animal.name
            binding.ageTextView.text = animal.age.toString()
            binding.breedTextView.text = animal.breed
            itemView.setOnClickListener {
                animalClickListener.onClick(animal, itemView)
            }
        }
    }

    companion object {
        private val animalDiffUtil = object : DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.age == newItem.age && oldItem.breed == newItem.breed
            }
        }
    }

    interface AnimalClickListener {
        fun onClick(animal: Animal, view: View)
    }
}
