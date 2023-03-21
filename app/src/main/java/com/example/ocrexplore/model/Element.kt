package com.example.ocrexplore.model


import com.google.gson.annotations.SerializedName

data class Element(
    @SerializedName("distance")
    val distance: Distance = Distance(),
    @SerializedName("duration")
    val duration: Duration = Duration(),
    @SerializedName("status")
    val status: String = ""
)