package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class TeamMemberDto (
    @SerializedName("memberId") val memberId : Int,
    @SerializedName("name") val name : String,
    @SerializedName("profileImageUrl") val profileImageUrl : String
    )