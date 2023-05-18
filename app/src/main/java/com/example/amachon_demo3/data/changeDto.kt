package com.example.amachon_demo3.data

import com.google.gson.annotations.SerializedName

data class changeDto (
    @SerializedName("changeTechTagDto") val changeTechTagDto : ChangeTechTagDto,
    @SerializedName("changeRegionTagDto") val changeRegionTagDto : ChangeRegionTagDto
        )