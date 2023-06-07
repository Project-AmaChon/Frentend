package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class MessageDto (
    @SerializedName("hoursAgo") val hoursAgo : Int,
    @SerializedName("lastMessage") val lastMessage : String,
    @SerializedName("memberId") val memberId : Int,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("profileUrl") val profileUrl : String,
    @SerializedName("roomId") val roomId : Int,
    @SerializedName("unReadMessageCount") val unReadMessageCount : Int
)