package com.example.deutschland.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val category: Category,
    val colour: String,
    val details: String,
    val id: Int,
    val main_image: String,
    val name: String,
    val price: Double,
    val size: String,
) : Parcelable