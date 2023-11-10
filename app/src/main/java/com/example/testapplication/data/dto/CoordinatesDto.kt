package com.example.testapplication.data.dto

import com.google.gson.annotations.SerializedName

data class CoordinatesDto(
    @SerializedName("x")
    val x: String,
    @SerializedName("y")
    val y: String,
    @SerializedName("z")
    val z: String,
)
