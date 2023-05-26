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
import com.example.amachon_demo3.databinding.FragmentTagplusBinding
import com.example.amachon_demo3.viewmodel.RegionTagSharedViewModel
import com.example.amachon_demo3.viewmodel.TechTagSharedViewModel


class TagPlusFragment : Fragment() {

    private lateinit var binding: FragmentTagplusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tagplus, container, false)

        binding.regionBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_tagPlusFragment_to_regionTagFragment)
        }

        val sharedViewModel = ViewModelProvider(requireActivity()).get(RegionTagSharedViewModel::class.java)
        sharedViewModel.getData().observe(viewLifecycleOwner) { value ->
            binding.tag.text = value.toString()
        }

        binding.techBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_tagPlusFragment_to_techTagFragment)
        }

        val sharedViewModel2 = ViewModelProvider(requireActivity()).get(TechTagSharedViewModel::class.java)
        sharedViewModel2.getData().observe(viewLifecycleOwner) { value ->
            binding.techtag.text = value.toString()
        }

        binding.nextBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_tagPlusFragment_to_newProjectOptionFragment)
        }

        return binding.root
    }

}