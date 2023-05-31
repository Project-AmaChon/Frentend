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
import com.example.amachon_demo3.data.RegionSearchDto
import com.example.amachon_demo3.data.TagDto
import com.example.amachon_demo3.databinding.FragmentRegionTagBinding
import com.example.amachon_demo3.network.Client
import com.example.amachon_demo3.viewmodel.RegionTagSharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegionTagFragment : Fragment() {

    private lateinit var binding: FragmentRegionTagBinding
    private lateinit var regiontagList: MutableList<TagDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_region_tag, container, false)

        Client.retrofitService.getRegionTags()?.enqueue(object : Callback<RegionSearchDto> {
            override fun onResponse(
                call: Call<RegionSearchDto>,
                response: Response<RegionSearchDto>
            ) {
                regiontagList = response.body()?.result!!
                val listview = binding.regionListview
                val adapter = RegionListViewAdapter(regiontagList)
                listview.adapter = adapter

                listview.setOnItemClickListener{ parent, view, position, id ->
                    val detaillistview = binding.regionDetailListview
                    val detailadapter = RegionDetailListViewAdapter(regiontagList[position].children)
                    detaillistview.adapter = detailadapter

                    detaillistview.setOnItemClickListener { parent, view, position2, id ->
                        val sharedViewModel = ViewModelProvider(requireActivity()).get(
                            RegionTagSharedViewModel::class.java)
                        sharedViewModel.setData(regiontagList[position].children[position2].toString())

                    }
                }
            }
            override fun onFailure(call: Call<RegionSearchDto>, t: Throwable) {

            }
        })
        binding.btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_regionTagFragment_to_tagPlusFragment)
        }
        return binding.root
    }
}