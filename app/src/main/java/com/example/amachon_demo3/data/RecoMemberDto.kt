package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class RecoMemberDto (
    @SerializedName("memberId") val memberId : Int,
    @SerializedName("name") val name : String,
    @SerializedName("profileImageUrl") val profileImageUrl : String,
    @SerializedName("recruitManagementId") val recruitManagementId : Int,
    @SerializedName("techTagNames") val techTagNames : MutableList<String>
        )