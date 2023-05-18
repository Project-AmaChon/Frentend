package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class ChangeTechTagDto (
    @SerializedName("techTagName") val techTagName: MutableList<String>
        )