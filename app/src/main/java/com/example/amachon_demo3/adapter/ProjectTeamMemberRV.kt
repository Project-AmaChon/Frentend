package com.example.amachon_demo3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amachon_demo3.R
import com.example.amachon_demo3.data.TeamMemberDto

class ProjectTeamMemberRV (val items : MutableList<TeamMemberDto>) : RecyclerView.Adapter<ProjectTeamMemberRV.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectTeamMemberRV.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.projectteammemberrvitem, parent, false)

            return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectTeamMemberRV.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item : TeamMemberDto) {
            val hi = itemView.findViewById<TextView>(R.id.techrv)
            hi.text = item.name
        }
    }
}