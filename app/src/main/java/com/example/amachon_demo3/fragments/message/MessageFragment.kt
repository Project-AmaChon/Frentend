package com.example.amachon_demo3.fragments.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.amachon_demo3.R
import com.example.amachon_demo3.databinding.FragmentMessageBinding


class MessageFragment : Fragment() {

    private lateinit var binding: FragmentMessageBinding
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val messageList: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false)
        listView = binding.root.findViewById(R.id.listView)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, messageList)
        listView.adapter = adapter

        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageFragment_to_homeFragment)
        }

        binding.projecttap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageFragment_to_projectFragment)
        }

        binding.mypagetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageFragment_to_myPageFragment)
        }


        //리스트뷰 테스트 코드 입니다!!!!
        val newMessage = "새로운 쪽지 내용"
        messageList.add(newMessage)
        adapter.notifyDataSetChanged()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}