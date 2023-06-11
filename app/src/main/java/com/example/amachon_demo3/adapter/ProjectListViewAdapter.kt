package com.example.amachon_demo3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.amachon_demo3.R
import com.example.amachon_demo3.data.ProjectDto

class ProjectListViewAdapter(val List : MutableList<ProjectDto>) : BaseAdapter() {
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
            convertView = LayoutInflater.from(p2?.context).inflate(R.layout.projectlistview_item, p2, false)
        }

        val mainTitle = convertView!!.findViewById<TextView>(R.id.projectMainTitle)
        mainTitle.text = List[p0].title


        val subTitle = convertView!!.findViewById<TextView>(R.id.projectSubTitle)
        subTitle.text = List[p0].tagNames[0]

        val subTitle2 = convertView!!.findViewById<TextView>(R.id.projectSubTitle2)
        subTitle2.text = List[p0].tagNames[1]


        return convertView!!
    }

}