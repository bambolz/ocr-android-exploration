package com.example.ocrexplore.model


import com.google.gson.annotations.SerializedName

data class Row(
    @SerializedName("elements")
    val elements: List<Element> = listOf()
)