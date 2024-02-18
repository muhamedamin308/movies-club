package com.example.androidpractice1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.androidpractice1.R
import com.example.androidpractice1.domain.SliderItems

class SliderAdapter(
    private val sliderItems: ArrayList<SliderItems>, private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    private lateinit var context: Context


    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imageSlider: ImageView

        init {
            imageSlider = itemView.findViewById(R.id.imageSlider)
        }

        fun setImageSlider(slider: SliderItems) {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(60))
            Glide.with(context).load(slider.image).apply(requestOptions).into(imageSlider)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        context = parent.context
        return SliderViewHolder(
            LayoutInflater.from(context).inflate(R.layout.slider, parent, false)
        )
    }

    override fun getItemCount(): Int = sliderItems.size

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImageSlider(sliderItems[position])
        if (position == sliderItems.size - 2) viewPager2.post(runnable)
    }

    private val runnable = Runnable {
        run {
            sliderItems.addAll(sliderItems)
            notifyItemChanged(0)
        }
    }
}