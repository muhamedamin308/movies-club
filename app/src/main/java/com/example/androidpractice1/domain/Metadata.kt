package com.example.androidpractice1.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Metadata {
    @SerializedName("current_page")
    @Expose
    var currentPage: String? = null

    @SerializedName("per_page")
    @Expose
    var perPage: Int? = null

    @SerializedName("page_count")
    @Expose
    var pageCount: Int? = null

    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null
}
