package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class EmailCheckDto (
        @SerializedName("email") val email : String,
        @SerializedName("code") val code : String
        )