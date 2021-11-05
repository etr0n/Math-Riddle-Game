package com.example.mathriddles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView


class BAdapter(val levels: List<Level>, private val onClick:((levelId:Int)->Unit)? = null) :
        RecyclerView.Adapter<BAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.allLevelbutton)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_all_level, parent, false))
    }
    override fun getItemCount(): Int{

        return levels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = levels[position]
        holder.button.text = data.levelId.toString()
        if(data.indicator)
        {
            holder.button.isEnabled = true
        }
        holder.button.setOnClickListener{
            when(position){
                data.levelId-1 -> {
                    onClick?.invoke(data.levelId)
                }
                else -> return@setOnClickListener
            }
        }
    }
}