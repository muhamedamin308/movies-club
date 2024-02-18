package com.example.androidpractice1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice1.databinding.CategoryLayoutBinding
import com.example.androidpractice1.domain.CategoryItem

class CategoriesAdapter(private val categories: ArrayList<CategoryItem>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    private lateinit var context: Context

    inner class CategoriesViewHolder(val binding: CategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        context = parent.context
        val binding = CategoryLayoutBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.categoryName.text = categories[position].name
        holder.binding.categoryItem.setOnClickListener {
        }
    }
}