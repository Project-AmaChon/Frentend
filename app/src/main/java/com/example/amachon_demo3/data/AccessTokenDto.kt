package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class AccessTokenDto (
    @SerializedName("grantType") val grantType : String,
    @SerializedName("accessToken") val accessToken : String,
    @SerializedName("refreshToken") val refreshToken : String,
    @SerializedName("memberId") val memberId : Int
        )