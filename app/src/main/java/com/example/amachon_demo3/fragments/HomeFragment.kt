package com.example.amachon_demo3.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.amachon_demo3.R
import com.example.amachon_demo3.adapter.CurrentListViewAdapter
import com.example.amachon_demo3.data.CurrentProjectDto
import com.example.amachon_demo3.databinding.FragmentHomeBinding
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        Client.retrofitService.getHome()?.enqueue(object : Callback<CurrentProjectDto>{
            override fun onResponse(
                call: Call<CurrentProjectDto>,
                response: Response<CurrentProjectDto>
            ) {

                val currentListViewAdapter = binding.homeprojectlisitview
                val adapter = CurrentListViewAdapter(response.body()!!.result)

                currentListViewAdapter.adapter = adapter
            }

            override fun onFailure(call: Call<CurrentProjectDto>, t: Throwable) {
                Log.d("tlqkf", t.toString())
            }

        })

        binding.testBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_projectPageFragment)
        }

        binding.projecttap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_projectFragment)
        }
        binding.massagetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_messageFragment)
        }
        binding.mypagetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_myPageFragment)
        }

        return binding.root
    }
}