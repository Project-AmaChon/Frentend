package com.example.amachon_demo3.fragments.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.amachon_demo3.R
import com.example.amachon_demo3.adapter.CurrentListViewAdapter
import com.example.amachon_demo3.data.CurrentProjectDto
import com.example.amachon_demo3.data.ProfileGetDto
import com.example.amachon_demo3.databinding.FragmentMyPageBinding
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)

        Client.retrofitService.getProfile()?.enqueue(object : Callback<ProfileGetDto> {
            override fun onResponse(
                call: Call<ProfileGetDto>,
                response: Response<ProfileGetDto>
            ) {

                Glide.with(this@MyPageFragment).load(response.body()?.result?.profileImageUrl.toString()).into(binding.imageView)
                binding.btnCall.text = response.body()?.result?.blogUrl.toString()
                binding.btnCall4.text = response.body()?.result?.githubUrl.toString()
                binding.editTextTextMultiLine.text = response.body()?.result?.introduction.toString()

                binding.textView5.text = response.body()?.result?.nickname.toString()
                binding.textView11.text = response.body()?.result?.regionTag.toString()
                binding.textView15.text = response.body()?.result!!.techTags[0]
                binding.textView16.text = response.body()?.result!!.techTags[1] // Null에 대한 예외처리 해야함

            }

            override fun onFailure(call: Call<ProfileGetDto>, t: Throwable) {

            }


        })

        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_homeFragment)
        }

        binding.massagetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_messageFragment)
        }

        binding.projecttap.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_projectFragment)
        }

        return binding.root
    }
}