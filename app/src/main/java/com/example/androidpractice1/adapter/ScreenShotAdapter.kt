package com.example.androidpractice1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.androidpractice1.databinding.ScreenshotLayoutBinding
import com.example.androidpractice1.domain.FilmDetails

class ScreenShotAdapter(private val filmDetails: FilmDetails): RecyclerView.Adapter<ScreenShotAdapter.ScreenShotViewHolder>() {
    private lateinit var context: Context
    inner class ScreenShotViewHolder(val binding: ScreenshotLayoutBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenShotViewHolder {
        context = parent.context
        val binding = ScreenshotLayoutBinding.inflate(LayoutInflater.from(context),
            parent, false)
        return ScreenShotViewHolder(binding)
    }

    override fun getItemCount(): Int = filmDetails.images?.size ?: 0

    override fun onBindViewHolder(holder: ScreenShotViewHolder, position: Int) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterInside(), RoundedCorners(30))
        Glide.with(context)
            .load(filmDetails.images?.get(position))
            .apply(requestOptions)
            .into(holder.binding.screenshot)
    }
}