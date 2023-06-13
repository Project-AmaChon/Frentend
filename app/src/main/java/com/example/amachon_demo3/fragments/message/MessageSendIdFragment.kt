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
import com.example.amachon_demo3.data.MsgResDto
import com.example.amachon_demo3.data.SendMessageDto
import com.example.amachon_demo3.databinding.FragmentMessageSendIdBinding
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding : FragmentMessageSendIdBinding

class MessageSendIdFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message_send_id, container, false)


        binding.sendingBtn.setOnClickListener {
            var msgcontent = binding.msgcontent.text.toString()
            var memId = Client.watchmemberId

            // Toast.makeText(requireContext(), Client.roomId.toString(), Toast.LENGTH_LONG).show()

            //
            Client.retrofitService.sendMessageId(memId, SendMessageDto(msgcontent)).enqueue(
                object : Callback<MsgResDto> {
                    override fun onResponse(
                        call: Call<MsgResDto>,
                        response: Response<MsgResDto>
                    ) {
                        Toast.makeText(requireContext(), "전송 완료", Toast.LENGTH_LONG).show()
                        it.findNavController().navigate(R.id.action_messageSendIdFragment_to_messageFragment)
                    }

                    override fun onFailure(call: Call<MsgResDto>, t: Throwable) {
                        Toast.makeText(requireContext(), "전송 실패", Toast.LENGTH_LONG).show()
                    }
                })
        }

        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageSendIdFragment_to_homeFragment)
        }

        binding.projecttap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageSendIdFragment_to_projectFragment)
        }

        binding.mypagetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageSendIdFragment_to_myPageFragment)
        }

        binding.massagetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_messageSendIdFragment_to_messageFragment)
        }

        return binding.root
    }
}