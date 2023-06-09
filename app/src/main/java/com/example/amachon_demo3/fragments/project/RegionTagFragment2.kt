package com.example.amachon_demo3.fragments.project

import android.os.Bundle
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
import com.example.amachon_demo3.databinding.FragmentRegionTag2Binding
import com.example.amachon_demo3.databinding.FragmentRegionTagBinding
import com.example.amachon_demo3.network.Client
import com.example.amachon_demo3.viewmodel.RegionTagSharedViewModel
import com.example.amachon_demo3.viewmodel.RegionTagSharedViewModel2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegionTagFragment2 : Fragment() {

    private lateinit var binding: FragmentRegionTag2Binding
    private lateinit var regiontagList: MutableList<TagDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_region_tag2, container, false)

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
                            RegionTagSharedViewModel2::class.java)
                        sharedViewModel.setData(regiontagList[position].children[position2].toString())

                    }
                }
            }
            override fun onFailure(call: Call<RegionSearchDto>, t: Throwable) {

            }
        })
        binding.btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_regionTagFragment2_to_projectFragment)
        }
        return binding.root
    }
}