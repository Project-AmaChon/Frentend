package com.example.amachon_demo3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.amachon_demo3.R
import com.example.amachon_demo3.data.RecoMemberDto

class RecommendMemberAdapter (val List : MutableList<RecoMemberDto>) : BaseAdapter(){
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(p0: Int): Any {
        return List[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1

        if (convertView == null) {
            convertView = LayoutInflater.from(p2?.context).inflate(R.layout.recommend_member_listview_item, p2, false)
        }

        val name = convertView!!.findViewById<TextView>(R.id.recommend_name)
        name.text = List[p0].name

        return convertView!!
    }
}