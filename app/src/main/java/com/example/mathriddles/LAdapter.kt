package com.example.mathriddles


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class LAdapter(val levels: List<Level>) :
        RecyclerView.Adapter<LAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
       val levelText = view.findViewById(R.id.levelItem_TextView) as TextView
        val dateText = view.findViewById(R.id.dateItem_textView) as TextView
        val bestTimeText = view.findViewById(R.id.bestTimeItem_textView) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }
    override fun getItemCount(): Int{

        return levels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = levels[position]
        val time = convertLongToTime(data.bestTime)
        holder.levelText.text = data.levelId.toString()
        holder.dateText.text = data.date
        holder.bestTimeText.text = time

    }
    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("mm:ss")
        return format.format(date)
    }




}