package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class ReMembercpresponeDto (
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : MutableList<RecoMemberDto>
        )