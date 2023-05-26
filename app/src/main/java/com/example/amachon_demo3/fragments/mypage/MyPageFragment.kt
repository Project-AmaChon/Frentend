package com.example.amachon_demo3.fragments.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.amachon_demo3.R
import com.example.amachon_demo3.databinding.FragmentMyPageBinding

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