package com.example.amachon_demo3.fragments.project

import android.app.Dialog
import android.content.Context
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
import com.example.amachon_demo3.adapter.ProjectListViewAdapter
import com.example.amachon_demo3.data.*
import com.example.amachon_demo3.databinding.FragmentProjectBinding
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectFragment : Fragment() {

    private lateinit var binding: FragmentProjectBinding
    //private lateinit var tagList : MutableList<TagDto>
    private lateinit var projectList : MutableList<ProjectDto>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project, container, false)

        /*
        binding.regionsearchBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_projectFragment_to_regionTagFragment)
        }
        */


        /*
        binding.regionsearchBtn.setOnClickListener {
            Client.retrofitService.getRegionTags()?.enqueue(object : Callback<RegionSearchDto>{
                override fun onResponse(
                    call: Call<RegionSearchDto>,
                    response: Response<RegionSearchDto>
                ) {
                    tagList = response.body()?.result!!
                }

                override fun onFailure(call: Call<RegionSearchDto>, t: Throwable) {

                }

            })
        }
         */
        /*
        binding.tagssearchBtn.setOnClickListener {
            Client.retrofitService.getTechTags()?.enqueue(object : Callback<TagsSearchDto>{
                override fun onResponse(
                    call: Call<TagsSearchDto>,
                    response: Response<TagsSearchDto>
                ) {
                    Log.d("regiontags", response.body()?.tagList.toString())
                }

                override fun onFailure(call: Call<TagsSearchDto>, t: Throwable) {
                }

            })
        }*/

        binding.searchBtn.setOnClickListener {
            val keyword = ""
            val regionTagNames = mutableListOf<String>("화성시")
            val techTagNames = mutableListOf<String>("Spring")

            Client.retrofitService.postSearch(SearchDto(keyword, regionTagNames, techTagNames)).enqueue(
                object : Callback<ProjectSearchDto> {
                    override fun onResponse(
                        call: Call<ProjectSearchDto>,
                        response: Response<ProjectSearchDto>
                    ) {
                        if (response.body()?.isSuccess!!) {
                            Log.d("MSG", "200 OK")
                        }
                        //response.body()?.result!![0].title
                        projectList = response.body()?.result!!

                        /*
                        Log.d("msg", "메세지 전")
                        Log.d("msg", msg)
                        Log.d("msg", "메세지 후")
                        Log.d("post", projectList.toString())
                         */

                        val listview = binding.projectListView
                        val adapter = ProjectListViewAdapter(projectList)

                        listview.adapter = adapter
                    }

                    override fun onFailure(call: Call<ProjectSearchDto>, t: Throwable) {
                    }
                }
            )
        }

        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_projectFragment_to_homeFragment)
        }

        binding.massagetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_projectFragment_to_messageFragment)
        }

        binding.mypagetap.setOnClickListener {
            it.findNavController().navigate(R.id.action_projectFragment_to_myPageFragment)
        }

        binding.newprojectplusBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_projectFragment_to_newProjectFragment)
        }

        return binding.root
    }
}