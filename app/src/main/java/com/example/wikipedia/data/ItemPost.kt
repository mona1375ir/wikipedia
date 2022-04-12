package com.example.wikipedia.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemPost(
    //for explore fragment
    val imgUrl: String,
    val txtTitle: String,
    val txtSubtitle: String,
    val txtDetail: String,
    //for trend fragment
    val isTrend: Boolean,
    val inSight: String,
):Parcelable
