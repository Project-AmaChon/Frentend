package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class TagDto (
    @SerializedName("name") val name : String,
    @SerializedName("children") val children : MutableList<String>
    )