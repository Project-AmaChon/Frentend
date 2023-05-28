package com.example.amachon_demo3.fragments.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amachon_demo3.R
import com.example.amachon_demo3.adapter.NowMemberListViewAdapter
import com.example.amachon_demo3.adapter.ProjectTeamMemberRV
import com.example.amachon_demo3.adapter.TechRVAdapter
import com.example.amachon_demo3.data.BaseDto
import com.example.amachon_demo3.data.ProjectDetailDto
import com.example.amachon_demo3.data.ReMembercpresponeDto
import com.example.amachon_demo3.data.RecoMemberDto
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
            Client.retrofitService.getReCoMember(586).enqueue(object : Callback<ReMembercpresponeDto>{
                override fun onResponse(
                    call: Call<ReMembercpresponeDto>,
                    response: Response<ReMembercpresponeDto>
                ) {

                }

                override fun onFailure(call: Call<ReMembercpresponeDto>, t: Throwable) {
                }

            })
        }

        binding.renow.setOnClickListener {
            var List = mutableListOf<RecoMemberDto>()
            Client.retrofitService.getNowMember(586).enqueue(object : Callback<ReMembercpresponeDto> {
                override fun onResponse(
                    call: Call<ReMembercpresponeDto>,
                    response: Response<ReMembercpresponeDto>
                ) {
                    List = response.body()!!.result
                    val dialogBuilder = AlertDialog.Builder(requireContext())

                    val inflater = layoutInflater
                    val dialogView = inflater.inflate(R.layout.now_member_dialog, null)
                    dialogBuilder.setView(dialogView)

                    val nowListview = dialogView.findViewById<ListView>(R.id.nowlistview)
                    val adapter = NowMemberListViewAdapter(List)

                    nowListview.adapter = adapter

                    val alertDialog = dialogBuilder.create()
                    alertDialog.show()
                }

                override fun onFailure(call: Call<ReMembercpresponeDto>, t: Throwable) {

                }

            })

        }

        binding.hey.setOnClickListener {
            Client.retrofitService.projectApply(586).enqueue(object : Callback<BaseDto> {
                override fun onResponse(call: Call<BaseDto>, response: Response<BaseDto>) {
                    Toast.makeText(requireContext(), "참가 신청 완료", Toast.LENGTH_LONG).show()
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

                val MemberRv = binding.memberRV
                val memberrvAdapter = ProjectTeamMemberRV(response.body()!!.result.teamMembers)

                MemberRv.adapter = memberrvAdapter
                MemberRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


                binding.texttext.text = "현재 참가 중인 인원 (" + response.body()?.result?.teamMembers?.size.toString() + "/" + response.body()?.result?.recruitNumber.toString() + ")"

                if (Client.memberId == response.body()?.result?.leaderId) {
                    binding.memberreco.visibility = View.VISIBLE
                    binding.renow.visibility = View.VISIBLE
                }
                else {
                    var IsinMember = false

                    for (item in response.body()?.result?.teamMembers!!) {
                        if (item.memberId == Client.memberId) {
                            IsinMember = true
                        }
                    }

                    if (!IsinMember) {
                        binding.hey.visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<ProjectDetailDto>, t: Throwable) {

            }

        })

        return binding.root
    }
}