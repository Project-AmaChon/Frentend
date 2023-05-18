package com.example.amachon_demo3.fragments.project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.amachon_demo3.R
import com.example.amachon_demo3.adapter.RegionDetailListViewAdapter
import com.example.amachon_demo3.adapter.RegionListViewAdapter
import com.example.amachon_demo3.data.TagDto
import com.example.amachon_demo3.data.TagsSearchDto
import com.example.amachon_demo3.databinding.FragmentTechTagBinding
import com.example.amachon_demo3.network.Client
import com.example.amachon_demo3.viewmodel.TechTagSharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TechTagFragment : Fragment() {
    private lateinit var binding: FragmentTechTagBinding
    private lateinit var techtagList: MutableList<TagDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tech_tag, container, false)

        Client.retrofitService.getTechTags()?.enqueue(object : Callback<TagsSearchDto> {
            override fun onResponse(
                call: Call<TagsSearchDto>,
                response: Response<TagsSearchDto>
            ) {
                Log.d("error2", response.body().toString())
                techtagList = response.body()?.result!!
                val listview = binding.techListview

                val adapter = RegionListViewAdapter(techtagList)
                listview.adapter = adapter

                listview.setOnItemClickListener{ parent, view, position, id ->
                    val detaillistview = binding.techDetailListview
                    val detailadapter = RegionDetailListViewAdapter(techtagList[position].children)
                    detaillistview.adapter = detailadapter

                    detaillistview.setOnItemClickListener { parent, view, position2, id ->
                        val sharedViewModel = ViewModelProvider(requireActivity()).get(
                            TechTagSharedViewModel::class.java)
                        sharedViewModel.setData(techtagList[position].children[position2].toString())

                    }
                }
            }
            override fun onFailure(call: Call<TagsSearchDto>, t: Throwable) {

            }
        })
        binding.btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_techTagFragment_to_tagPlusFragment)
        }

        return binding.root
    }
}