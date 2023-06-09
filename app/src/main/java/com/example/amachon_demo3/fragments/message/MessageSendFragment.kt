package com.example.amachon_demo3.fragments.message

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.amachon_demo3.R
import com.example.amachon_demo3.data.MsgResDto
import com.example.amachon_demo3.data.SendMessageDto
import com.example.amachon_demo3.databinding.FragmentMessageSendBinding
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding : FragmentMessageSendBinding

class MessageSendFragment : Fragment() {

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message_send, container, false)

        Log.d("MSG2-send", Client.roomId.toString())

        binding.sendingBtn.setOnClickListener {
            var msgcontent = binding.msgcontent.text.toString()
            Log.d("MSG2-send-intern", Client.roomId.toString())
            // var msgcontent = "디자인 예쁘게 부탁"
            var roomId = Client.roomId

            // Toast.makeText(requireContext(), Client.roomId.toString(), Toast.LENGTH_LONG).show()

            Client.retrofitService.sendMessageRoom(roomId, SendMessageDto(msgcontent)).enqueue(
                object : Callback<MsgResDto> {
                    override fun onResponse(
                        call: Call<MsgResDto>,
                        response: Response<MsgResDto>
                    ) {
                        Toast.makeText(requireContext(), "전송 완료", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<MsgResDto>, t: Throwable) {
                        Toast.makeText(requireContext(), "전송 실패", Toast.LENGTH_LONG).show()
                    }
                })
        }

        return binding.root
    }
}