package com.example.androidpractice1.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FilmItem(
    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null,
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata? = null
)
