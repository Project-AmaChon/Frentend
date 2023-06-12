package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

class ProfileDto (
        @SerializedName("blogUrl") val blogUrl : String,
        @SerializedName("description") val description : String,
        @SerializedName("githubUrl") val githubUrl : String,
        @SerializedName("introduction") val introduction : String,
        @SerializedName("nickname") val nickname : String,
        @SerializedName("profileImageUrl") val profileImageUrl : String,
        @SerializedName("regionTag") val regionTag : String,
        @SerializedName("techTags") val techTags : MutableList<String>
        )