package com.example.network

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private var list: List<PingResult>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val time: TextView = view.findViewById(R.id.timeTxt)
        val url: TextView = view.findViewById(R.id.urlTxt)
        val latency: TextView = view.findViewById(R.id.latencyTxt)
        val netType: TextView = view.findViewById(R.id.networkTypeText)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.time.text = item.time
        holder.url.text = item.url
        holder.latency.text = if (item.latency == -1L) "Error" else "${item.latency}ms"
        holder.netType.text = item.networkType

        if (item.latency == -1L) {
            holder.latency.setTextColor(Color.BLACK)
        } else if (item.latency < 100) {
            holder.latency.setTextColor(Color.parseColor("#27AE60"))
        } else {
            holder.latency.setTextColor(Color.parseColor("#E67E22"))
        }

        if (item.networkType == "Wi-Fi") {
            holder.netType.setTextColor(Color.parseColor("#3498DB"))
        } else if (item.networkType == "Cellular") {
            holder.netType.setTextColor(Color.parseColor("#E67E22"))
        }
    }

    fun updateData(newList: List<PingResult>) {
        this.list = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ping, parent, false)
        return ViewHolder(view)
    }
}