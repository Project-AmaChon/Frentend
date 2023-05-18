package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class LoginDto (
    @SerializedName("email") val email : String,
    @SerializedName("password") val password : String
        )