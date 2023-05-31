package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class ProjectDto (
    @SerializedName("projectID") val projectID : Int,
    @SerializedName("title") val title : String,
    @SerializedName("recruitDeadline") val recruitDeadline : String,
    @SerializedName("recruitNumber") val recruitNumber : Int,
    @SerializedName("currentNumber") val currentNumber : Int,
    @SerializedName("tagNames") val tagNames : MutableList<String>
    )