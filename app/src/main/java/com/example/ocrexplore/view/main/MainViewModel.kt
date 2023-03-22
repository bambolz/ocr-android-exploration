package com.example.ocrexplore.view.main

import android.location.Location
import android.location.LocationManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ocrexplore.BuildConfig
import com.example.ocrexplore.network.ApiInterface
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val _uiModel: MutableStateFlow<MainUIModel> = MutableStateFlow(MainUIModel())
    val uiModel = _uiModel.asStateFlow()

    private fun getDistance(start: Location, end: Location) {
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

    fun calculateDistanceToPlazaIndonesia(origin: Location) {
        setCurrentLocation(origin)
        var plazaIndonesia = Location(LocationManager.GPS_PROVIDER)
        plazaIndonesia.latitude = -6.1930672
        plazaIndonesia.longitude = 106.8217313
        getDistance(origin, plazaIndonesia)
    }

    fun storeToDB() {
        val database = Firebase.database
        val myRef = database.getReference("data")
        myRef.setValue(uiModel.value)
    }

    fun setOcrResult(resultOcr: String) {
        _uiModel.value = _uiModel.value.copy(text = resultOcr)
    }

    private fun setCurrentLocation(location: Location) {
        _uiModel.value =
            _uiModel.value.copy(
                latitude = location.latitude,
                longitude = location.longitude,
            )
    }
}
