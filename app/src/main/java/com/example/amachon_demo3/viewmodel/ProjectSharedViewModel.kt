package com.example.amachon_demo3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class ProjectSharedViewModel : ViewModel() {
    private val recruitNumber = MutableLiveData<Int>()
    private val developPeriod = MutableLiveData<LocalDate>()
    private val recruitDeadline = MutableLiveData<LocalDate>()

    fun setrecruitNumber(value: Int) {
        recruitNumber.value = value
    }

    fun getrecruitNumber(): LiveData<Int> {
        return recruitNumber
    }

    fun setdevelopPeriod(value: LocalDate) {
        developPeriod.value = value
    }

    fun getdevelopPeriod(): LiveData<LocalDate> {
        return developPeriod
    }

    fun setrecruitDeadline(value: LocalDate) {
        recruitDeadline.value = value
    }

    fun getrecruitDeadline(): LiveData<LocalDate> {
        return recruitDeadline
    }
}