package com.example.mathriddles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class SAdapter(val statistics: List<Statistics>):
    RecyclerView.Adapter<SAdapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val dateText = view.findViewById(R.id.datestatitem_textView) as TextView
        val timeText = view.findViewById(R.id.timestatitem_textView) as TextView
        val mistakesText = view.findViewById(R.id.mistakestatitem_textView) as TextView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_statistics, parent, false)
        )
    }
    override fun getItemCount(): Int{
        return statistics.size
    }

    override fun onBindViewHolder(holder: SAdapter.ViewHolder, position: Int) {
        val data = statistics[position]
        val time = convertLongToTime(data.time)
        holder.dateText.text = data.date
        holder.timeText.text = time
        holder.mistakesText.text = data.mistakes.toString()
    }
    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("mm:ss")
        return format.format(date)
    }

}