package com.example.wikipedia.data

data class ItemPost(
    //for explore fragment
    val imgUrl: String,
    val txtTitle: String,
    val txtSubtitle: String,
    val txtDetail: String,
    //for trend fragment
    val isTrend: Boolean,
    val inSight: String,
)
