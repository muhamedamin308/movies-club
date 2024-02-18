package com.example.androidpractice1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice1.databinding.GenersLayoutBinding
import com.example.androidpractice1.domain.FilmDetails

class SubCategoryAdapter(private val filmDetails: FilmDetails): RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder>() {
    inner class SubCategoryViewHolder(val binding: GenersLayoutBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val binding = GenersLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return SubCategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = filmDetails.genres?.size ?: 0

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        if (position <= (filmDetails.genres?.size?.minus(2) ?: 0)) {
            holder.binding.genreName.text = "${filmDetails.genres?.get(position)} . "
        } else {
            holder.binding.genreName.text = filmDetails.genres?.get(position)
        }
    }
}