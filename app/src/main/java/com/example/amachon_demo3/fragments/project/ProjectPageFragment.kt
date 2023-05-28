package com.example.amachon_demo3.fragments.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amachon_demo3.R
import com.example.amachon_demo3.adapter.TechRVAdapter
import com.example.amachon_demo3.data.BaseDto
import com.example.amachon_demo3.data.ProjectDetailDto
import com.example.amachon_demo3.databinding.FragmentProjectPageBinding
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectPageFragment : Fragment() {

    private lateinit var binding : FragmentProjectPageBinding
    private lateinit var List : MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_page, container, false)

        binding.memberreco.setOnClickListener {

        }

        binding.renow.setOnClickListener {

        }

        binding.hey.setOnClickListener {
            Client.retrofitService.projectApply(586).enqueue(object : Callback<BaseDto> {
                override fun onResponse(call: Call<BaseDto>, response: Response<BaseDto>) {

                }

                override fun onFailure(call: Call<BaseDto>, t: Throwable) {

                }

            })
        }

        Client.retrofitService.getProject(586).enqueue(object : Callback<ProjectDetailDto> {
            override fun onResponse(
                call: Call<ProjectDetailDto>,
                response: Response<ProjectDetailDto>
            ) {
                binding.title.text = response.body()?.result?.title.toString()
                binding.content.text = response.body()?.result?.description.toString()

                List = response.body()?.result?.techTagNames!!

                val techRv = binding.techRv
                val rvAdapter = TechRVAdapter(List)

                techRv.adapter = rvAdapter
                techRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

            override fun onFailure(call: Call<ProjectDetailDto>, t: Throwable) {

            }

        })

        return binding.root
    }
}