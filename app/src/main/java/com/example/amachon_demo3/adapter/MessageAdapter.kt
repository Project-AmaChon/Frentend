package com.example.amachon_demo3.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.amachon_demo3.R

class MessageAdapter(private val context: Context, private val messages: List<String>) : BaseAdapter() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return messages.size
    }

    override fun getItem(position: Int): Any {
        return messages[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false)
        }

        val messageTextView = view?.findViewById<TextView>(R.id.messageTextView)
        val message = messages[position]
        messageTextView?.text = message

        return view!!
    }
}