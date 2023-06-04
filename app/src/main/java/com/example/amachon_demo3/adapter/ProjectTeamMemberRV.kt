package com.example.amachon_demo3.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amachon_demo3.R
import com.example.amachon_demo3.data.BaseDto
import com.example.amachon_demo3.data.TeamMemberDto
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectTeamMemberRV (val items : MutableList<TeamMemberDto>, val projectleaderId : Int) : RecyclerView.Adapter<ProjectTeamMemberRV.ViewHolder>(){
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

            val rv_img = itemView.findViewById<ImageView>(R.id.img)
            Glide.with(itemView).load(item.profileImageUrl).into(rv_img)
            if (projectleaderId == Client.memberId) {
                val cancelbtn = itemView.findViewById<ImageView>(R.id.cancel)
                cancelbtn.visibility = View.VISIBLE

                cancelbtn.setOnClickListener {
                    Client.retrofitService.kickMember(projectleaderId, item.memberId).enqueue(object : Callback<BaseDto>{
                        override fun onResponse(call: Call<BaseDto>, response: Response<BaseDto>) {
                        }

                        override fun onFailure(call: Call<BaseDto>, t: Throwable) {

                        }

                    })
                }
            }
        }
    }
}