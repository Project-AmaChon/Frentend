package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class ProjectDetailInfoDto (
    @SerializedName("description") val description : String,
    @SerializedName("developPeriod") val developPeriod : String,
    @SerializedName("imageUrls") val imageUrls : MutableList<String>,
    @SerializedName("leaderId") val leaderId : Int,
    @SerializedName("projectId") val projectId : Int,
    @SerializedName("recruitDeadline") val recruitDeadline : String,
    @SerializedName("recruitNumber") val recruitNumber : Int,
    @SerializedName("regionTagName") val regionTagName : String,
    @SerializedName("teamMembers") val teamMembers : MutableList<TeamMemberDto>,
    @SerializedName("techTagNames") val techTagNames : MutableList<String>,
    @SerializedName("title") val title : String
        )