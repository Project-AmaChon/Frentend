package com.example.amachon_demo3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TechTagSharedViewModel : ViewModel() {
    private val data = MutableLiveData<String>()

    fun setData(value: String) {
        data.value = value
    }

    fun getData(): LiveData<String> {
        return data
    }
}