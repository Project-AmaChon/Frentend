package com.example.amachon_demo3.fragments.project

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.amachon_demo3.R
import com.example.amachon_demo3.data.ProjectCreateDto
import com.example.amachon_demo3.data.RespawnDto
import com.example.amachon_demo3.databinding.FragmentNewProjectBinding
import com.example.amachon_demo3.network.Client
import com.example.amachon_demo3.viewmodel.ProjectSharedViewModel
import com.example.amachon_demo3.viewmodel.RegionTagSharedViewModel
import com.example.amachon_demo3.viewmodel.TechTagSharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class NewProjectFragment : Fragment() {

    private lateinit var binding: FragmentNewProjectBinding
    private lateinit var recruitDeadline : LocalDate
    private lateinit var developPeriod : LocalDate
    lateinit var tagsList : MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        recruitDeadline = LocalDate.now()
        developPeriod = LocalDate.now()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_project, container, false)
        binding.optionBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_newProjectFragment_to_newProjectOptionFragment)
        }

        val projectViewModel = ViewModelProvider(requireActivity()).get(ProjectSharedViewModel::class.java)
        val regionTagViewModel = ViewModelProvider(requireActivity()).get(RegionTagSharedViewModel::class.java)
        val techTagViewModel = ViewModelProvider(requireActivity()).get(TechTagSharedViewModel::class.java)

        projectViewModel.getrecruitNumber().observe(viewLifecycleOwner) { value ->
            binding.number.text = value.toString()
        }

        projectViewModel.getrecruitDeadline().observe(viewLifecycleOwner) { value ->
            binding.projectdday.text = value.toString()
            recruitDeadline = value

        }

        projectViewModel.getdevelopPeriod().observe(viewLifecycleOwner) { value ->
            binding.memberdday.text = value.toString()
            developPeriod = value

        }

        regionTagViewModel.getData().observe(viewLifecycleOwner) { value ->
            binding.region.text = value.toString()
        }

        techTagViewModel.getData().observe(viewLifecycleOwner) { value ->
            binding.tech.text = value.toString()
            Log.d("value",value.toString())
            tagsList = mutableListOf()
            tagsList.add(value.toString())
        }

        binding.newprojectBtn.setOnClickListener {
            Client.retrofitService.createProject(ProjectCreateDto(
                binding.content.text.toString(),
                developPeriod.toString(),
                recruitDeadline.toString(),
                binding.number.text.toString().toInt(),
                binding.region.text.toString(),
                tagsList,
                binding.title.text.toString()
            )).enqueue(object : Callback<RespawnDto> {
                override fun onResponse(call: Call<RespawnDto>, response: Response<RespawnDto>) {
                    
                }

                override fun onFailure(call: Call<RespawnDto>, t: Throwable) {
                }

            })
            it.findNavController().navigate(R.id.action_newProjectFragment_to_projectFragment)
        }

        return binding.root
    }
}