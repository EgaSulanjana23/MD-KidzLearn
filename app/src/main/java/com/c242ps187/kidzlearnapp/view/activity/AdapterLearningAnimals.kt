package com.c242ps187.kidzlearnapp.view.activity

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.c242ps187.kidzlearnapp.data.response.AnimalsDataItem
import com.c242ps187.kidzlearnapp.databinding.ItemAnimalBinding

class AdapterLearningAnimals: ListAdapter<AnimalsDataItem, AdapterLearningAnimals.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    class MyViewHolder(private val binding: ItemAnimalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: AnimalsDataItem){
            Glide.with(binding.root)
                .load(animal.urlImages)
                .into(binding.ivAnimal)
            binding.tvAnimalName.text = animal.name
            binding.ivSound.setOnClickListener {
                val mediaPlayer = MediaPlayer().apply {
                    setDataSource(animal.urlSuaraHewan)
                    prepareAsync()
                    setOnPreparedListener { start() }
                }
                mediaPlayer.setOnCompletionListener {
                    it.release()
                }
            }
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnimalsDataItem>(){
            override fun areItemsTheSame(oldItem: AnimalsDataItem, newItem: AnimalsDataItem): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: AnimalsDataItem,
                newItem: AnimalsDataItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}