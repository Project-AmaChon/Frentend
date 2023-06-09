package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

class MessageDetailDto (
    @SerializedName("content") val content : String,
    @SerializedName("memberId") val memberId : Int,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("profileUrl") val profileUrl : String,
        )