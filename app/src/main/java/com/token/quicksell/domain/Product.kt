package com.token.quicksell.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val name: String,
    val category: String,
    val image: String,
): Parcelable
