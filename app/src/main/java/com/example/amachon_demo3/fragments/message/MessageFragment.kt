package com.example.amachon_demo3.fragments.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.amachon_demo3.R
import com.example.amachon_demo3.adapter.MessageListAdapter
import com.example.amachon_demo3.adapter.RegionDetailListViewAdapter
import com.example.amachon_demo3.adapter.RegionListViewAdapter
import com.example.amachon_demo3.data.MessageDto
import com.example.amachon_demo3.data.MessageListDto
import com.example.amachon_demo3.data.ProjectDto
import com.example.amachon_demo3.data.RegionSearchDto
import com.example.amachon_demo3.databinding.FragmentMessageBinding
import com.example.amachon_demo3.network.Client
import com.example.amachon_demo3.viewmodel.RegionTagSharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageFragment : Fragment() {

    private lateinit var binding: FragmentMessageBinding
    private lateinit var msgList : MutableList<MessageDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false)

        Client.retrofitService.getMessage()?.enqueue(object : Callback<MessageListDto> {
            override fun onResponse(
                call: Call<MessageListDto>,
                response: Response<MessageListDto>
            ) {
                msgList = response.body()?.result!!
                val listview = binding.listView

                val adapter = MessageListAdapter(msgList)
                listview.adapter = adapter
            }
            override fun onFailure(call: Call<MessageListDto>, t: Throwable) {

            }
        })

        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageFragment_to_homeFragment)
        }

        binding.projecttap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageFragment_to_projectFragment)
        }

        binding.mypagetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageFragment_to_myPageFragment)
        }

        return binding.root
    }
}