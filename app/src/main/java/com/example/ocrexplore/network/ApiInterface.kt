package com.example.ocrexplore.network

import com.example.ocrexplore.model.DirectionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("distancematrix/json")
    suspend fun getDistance(
        @Query("key") apiKey: String,
        @Query("destinations") destination: String,
        @Query("origins") origins: String,
        @Query("unit") unit: String? = "metrics",
    ): DirectionResponse
}
