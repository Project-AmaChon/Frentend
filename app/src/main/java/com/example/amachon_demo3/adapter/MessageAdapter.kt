package com.example.amachon_demo3.adapter

import com.example.amachon_demo3.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.amachon_demo3.data.MessageDto

class MessageListAdapter(private val context: Context, private val messageList: List<MessageDto>) : BaseAdapter() {

    override fun getCount(): Int {
        return messageList.size
    }

    override fun getItem(position: Int): Any {
        return messageList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        val messageDto = messageList[position]

        viewHolder.nicknameTextView.text = messageDto.nickname
        viewHolder.messageTextView.text = messageDto.lastMessage

        return view!!
    }

    private class ViewHolder(view: View) {
        val nicknameTextView: TextView = view.findViewById(R.id.nicknameTextView)
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
    }
}