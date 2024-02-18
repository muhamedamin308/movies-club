package com.example.androidpractice1.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Datum {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("poster")
    @Expose
    var poster: String? = null

    @SerializedName("year")
    @Expose
    var year: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("imdb_rating")
    @Expose
    var imdbRating: String? = null

    @SerializedName("genres")
    @Expose
    var genres: List<String>? = null

    @SerializedName("images")
    @Expose
    var images: List<String>? = null
}

