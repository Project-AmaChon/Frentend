package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class ProjectCreateDto (
    @SerializedName("description") val description : String,
    @SerializedName("developPeriod") val developPeriod : String,
    @SerializedName("recruitDeadline") val recruitDeadline : String,
    @SerializedName("recruitNumber") val recruitNumber : Int,
    @SerializedName("regionTagName") val regionTagName : String,
    @SerializedName("techTagNames") val techTagNames : MutableList<String>,
    @SerializedName("title") val title :String
        )