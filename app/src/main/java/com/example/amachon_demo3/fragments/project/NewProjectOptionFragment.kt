package com.example.amachon_demo3.fragments.project


import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.amachon_demo3.R
import com.example.amachon_demo3.databinding.FragmentNewProjectOptionBinding
import com.example.amachon_demo3.viewmodel.ProjectSharedViewModel
import com.example.amachon_demo3.viewmodel.RegionTagSharedViewModel
import com.example.amachon_demo3.viewmodel.TechTagSharedViewModel
import java.time.LocalDate


class NewProjectOptionFragment : Fragment() {

    private lateinit var binding: FragmentNewProjectOptionBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_project_option, container, false)

        binding.tagplus.setOnClickListener {
            it.findNavController().navigate(R.id.action_newProjectOptionFragment_to_tagPlusFragment)
        }

        val projectViewModel = ViewModelProvider(requireActivity()).get(ProjectSharedViewModel::class.java)

        binding.projectday.setOnClickListener {

            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            context?.let { it1 ->
                DatePickerDialog(it1, { _, year, month, day ->
                    run {
                        binding.projectday.setText(year.toString() + "." + (month + 1).toString() + "." + day.toString())
                        val developPeriod = LocalDate.of(year, month + 1, day)
                        projectViewModel.setdevelopPeriod(developPeriod)
                    }
                }, year, month, day)
            }?.show()


        }

        binding.projectfinishday.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            context?.let { it1 ->
                DatePickerDialog(it1, { _, year, month, day ->
                    run {
                        binding.projectfinishday.setText(year.toString() + "." + (month + 1).toString() + "." + day.toString())
                        val recruitDeadline = LocalDate.of(year, month + 1, day)
                        projectViewModel.setrecruitDeadline(recruitDeadline)
                    }
                }, year, month, day)
            }?.show()


        }
        val sharedViewModel = ViewModelProvider(requireActivity()).get(RegionTagSharedViewModel::class.java)
        sharedViewModel.getData().observe(viewLifecycleOwner) { value ->
            binding.region.text = value.toString()
        }
        val sharedViewModel2 = ViewModelProvider(requireActivity()).get(TechTagSharedViewModel::class.java)
        sharedViewModel2.getData().observe(viewLifecycleOwner) { value ->
            binding.tech.text = value.toString()
        }

        binding.check.setOnClickListener {
            projectViewModel.setrecruitNumber(binding.number.text.toString().toInt())
            it.findNavController().navigate(R.id.action_newProjectOptionFragment_to_newProjectFragment)
        }

        return binding.root
    }
}