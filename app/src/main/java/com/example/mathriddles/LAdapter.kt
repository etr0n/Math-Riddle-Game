package com.example.mathriddles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mathriddles.databinding.ItemBinding

class LAdapter (private val clickListener: LevelClickListener):
    ListAdapter<Level, LAdapter.ViewHolder>(LevelDiffCallback()){

    class ViewHolder(private  val binding: ItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(level: Level,clickListener: LevelClickListener){
            binding.level = level
            binding.clickListener = clickListener
        }
    }
    class LevelDiffCallback : DiffUtil.ItemCallback<Level>() {
        override fun areItemsTheSame(oldItem: Level, newItem: Level): Boolean {
            return oldItem.levelId == newItem.levelId
        }

        override fun areContentsTheSame(oldItem: Level, newItem: Level): Boolean {
            return oldItem == newItem
        }

    }
    class LevelClickListener( val clickListener: (level: Level)-> Unit){
        fun onClick(level: Level) {
            clickListener(level)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

}