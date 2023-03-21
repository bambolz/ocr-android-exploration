package com.example.ocrexplore.view

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ocrexplore.BuildConfig
import com.example.ocrexplore.network.ApiInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val _uiModel: MutableStateFlow<MainUIModel> = MutableStateFlow(MainUIModel())
    val uiModel = _uiModel.asStateFlow()

    fun getDistance(start: Location, end: Location) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiInterface = retrofit.create(ApiInterface::class.java)

        viewModelScope.launch {
            val res = service.getDistance(
                origins = "${start.latitude},${start.longitude}",
                destination = "${end.latitude},${end.longitude}",
                apiKey = BuildConfig.API_KEY,
            )
            _uiModel.value = _uiModel.value.copy(
                distance = res.rows[0].elements[0].distance.text,
                duration = res.rows[0].elements[0].duration.text,
            )
        }
    }
}
