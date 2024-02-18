package com.example.androidpractice1.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.androidpractice1.databinding.FilmLayoutBinding
import com.example.androidpractice1.domain.FilmItem
import com.example.androidpractice1.ui.activities.DetailsActivity

class FilmAdapter(
    private val films: FilmItem
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    private lateinit var context: Context

    inner class FilmViewHolder(val filmLayoutBinding: FilmLayoutBinding) :
        RecyclerView.ViewHolder(filmLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        context = parent.context
        val filmLayoutBinding =
            FilmLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        return FilmViewHolder(filmLayoutBinding)
    }

    override fun getItemCount(): Int = films.data?.size ?: 0

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.filmLayoutBinding.textView.text = films.data?.get(position)?.title ?: "Nil"
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterInside(), RoundedCorners(30))
        Glide.with(context)
            .load(films.data?.get(position)?.poster)
            .apply(requestOptions)
            .into(holder.filmLayoutBinding.filmPicture)
        holder.filmLayoutBinding.filmPicture.setOnClickListener {
            val intent =
                Intent(
                    holder.filmLayoutBinding.filmPicture.context,
                    DetailsActivity::class.java
                )
            intent.putExtra("id", films.data?.get(position)?.id)
            context.startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(context as Activity).toBundle())
        }
    }
}