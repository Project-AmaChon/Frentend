package com.example.amachon_demo3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amachon_demo3.R

class TechRVAdapter (val items : MutableList<String>) :
    RecyclerView.Adapter<TechRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvtechitem, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TechRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item : String) {
            val rv_text = itemView.findViewById<TextView>(R.id.techrv)
            rv_text.text = item
        }
    }
}