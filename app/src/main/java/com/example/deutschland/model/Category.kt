package com.example.deutschland.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val icon: String,
    val id: Int,
    val name: String
) : Parcelable