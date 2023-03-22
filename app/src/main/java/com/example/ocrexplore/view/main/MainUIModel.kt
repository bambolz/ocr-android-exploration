package com.example.ocrexplore.view.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainUIModel(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val distance: String = "",
    val duration: String = "",
    val text: String = "",
) : Parcelable
