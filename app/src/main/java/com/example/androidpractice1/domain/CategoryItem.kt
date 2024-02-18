package com.example.androidpractice1.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryItem(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String
)