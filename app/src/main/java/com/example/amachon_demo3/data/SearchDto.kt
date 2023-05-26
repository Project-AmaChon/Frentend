package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class SearchDto (
    @SerializedName("keyword") val keyword : String,
    @SerializedName("regionTagNames") val regionTagNames: MutableList<String>,
    @SerializedName("techTagNames") val techTagNames: MutableList<String>
    )