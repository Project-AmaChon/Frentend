package com.example.amachon_demo3.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.amachon_demo3.R
import com.example.amachon_demo3.data.BaseDto
import com.example.amachon_demo3.data.RecoMemberDto
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowMemberListViewAdapter(val List : MutableList<RecoMemberDto>) : BaseAdapter() {
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
        var holder : ViewHolder

        if (convertView == null) {
            convertView = LayoutInflater.from(p2?.context).inflate(R.layout.now_member_listview_item, p2, false)
            holder = ViewHolder()
            holder.okimageView = convertView!!.findViewById(R.id.ok)
            holder.rejectimageView = convertView!!.findViewById(R.id.reject)

            holder.okimageView!!.setOnClickListener {
                Client.retrofitService.projectAccept(List[p0].recruitManagementId).enqueue(object : Callback<BaseDto>{
                    override fun onResponse(call: Call<BaseDto>, response: Response<BaseDto>) {

                    }

                    override fun onFailure(call: Call<BaseDto>, t: Throwable) {

                    }

                })
            }
            holder.rejectimageView!!.setOnClickListener {
                Client.retrofitService.projectReject(List[p0].recruitManagementId).enqueue(object : Callback<BaseDto>{
                    override fun onResponse(call: Call<BaseDto>, response: Response<BaseDto>) {

                    }

                    override fun onFailure(call: Call<BaseDto>, t: Throwable) {
                    }

                })
            }
        }

        val name = convertView!!.findViewById<TextView>(R.id.name)
        name.text = List[p0].name

        return convertView!!
    }

    private class ViewHolder {
        var okimageView: ImageView? = null
        var rejectimageView: ImageView? = null
    }
}