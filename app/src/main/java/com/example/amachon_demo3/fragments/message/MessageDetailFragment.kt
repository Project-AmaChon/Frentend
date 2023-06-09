package com.example.amachon_demo3.fragments.message

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.amachon_demo3.R
import com.example.amachon_demo3.adapter.MessageDetailListAdapter
import com.example.amachon_demo3.adapter.MessageListAdapter
import com.example.amachon_demo3.data.MessageDetailDto
import com.example.amachon_demo3.data.MessageListDto
import com.example.amachon_demo3.data.MessageRoomDto
import com.example.amachon_demo3.databinding.FragmentMessageDetailBinding
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageDetailFragment : Fragment() {

    private lateinit var binding : FragmentMessageDetailBinding
    private lateinit var msgList : MutableList<MessageDetailDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message_detail, container, false)

        Client.retrofitService.getMessageRoom(Client.roomId).enqueue(object : Callback<MessageRoomDto> {
            override fun onResponse(
                call: Call<MessageRoomDto>,
                response: Response<MessageRoomDto>
            ) {
                Log.d("MSG", Client.roomId.toString())
                Toast.makeText(requireContext(), Client.roomId.toString(), Toast.LENGTH_LONG).show()

                msgList = response.body()?.result!!
                val listview = binding.listView

                val adapter = MessageDetailListAdapter(msgList)
                listview.adapter = adapter
            }

            override fun onFailure(call: Call<MessageRoomDto>, t: Throwable) {

            }

        })

        binding.sendBtn.setOnClickListener {
            Log.d("MSG2", Client.roomId.toString())
            Toast.makeText(requireContext(), Client.roomId.toString(), Toast.LENGTH_LONG).show()
            it.findNavController().navigate(R.id.action_messageDetailFragment_to_messageSendFragment)
        }

        return binding.root
    }
}