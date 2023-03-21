package com.example.ocrexplore.model

import com.example.ocrexplore.model.Row
import com.google.gson.annotations.SerializedName

data class DirectionResponse(
    @SerializedName("destination_addresses")
    val destinationAddresses: List<String> = listOf(),
    @SerializedName("origin_addresses")
    val originAddresses: List<String> = listOf(),
    @SerializedName("rows")
    val rows: List<Row> = listOf(),
    @SerializedName("status")
    val status: String = "",
)
