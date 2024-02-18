package com.example.androidpractice1.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.androidpractice1.Constants
import com.example.androidpractice1.adapter.ScreenShotAdapter
import com.example.androidpractice1.adapter.SubCategoryAdapter
import com.example.androidpractice1.databinding.ActivityDetailsBinding
import com.example.androidpractice1.domain.FilmDetails
import com.google.gson.Gson

class DetailsActivity : AppCompatActivity() {
    private lateinit var requestQueue: RequestQueue
    private lateinit var detailsRequest: StringRequest
    private var id: Int = 0
    private lateinit var screenShotAdapter: RecyclerView.Adapter<*>
    private lateinit var subCategoryAdapter: RecyclerView.Adapter<*>
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(binding.root)
        binding.back.setOnClickListener { finish() }
        id = intent.getIntExtra("id", 0)
        initUI()
        initRequests()
    }
    private fun initUI() {
        binding.apply {
            subCategoryRec.layoutManager =
                LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
            screenShotRec.layoutManager =
                LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initRequests() {
        requestQueue = Volley.newRequestQueue(this)
        binding.apply {
            detailsRequest =
                StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/$id", {
                    val gson = Gson()
                    val film: FilmDetails = gson.fromJson(it, FilmDetails::class.java)
                    Glide.with(this@DetailsActivity)
                        .load(film.poster)
                        .into(filmPoster)
                    filmTitle.text = film.title
                    imdbRate.text = "${film.imdbRating} / 10"
                    filmYear.text = film.year
                    filmCountry.text = film.country
                    filmTime.text = film.runtime
                    filmSummary.text = film.plot
                    actors.text = film.actors
                    screenShotAdapter = ScreenShotAdapter(film)
                    screenShotRec.adapter = screenShotAdapter
                    subCategoryAdapter = SubCategoryAdapter(film)
                    subCategoryRec.adapter = subCategoryAdapter
                }, {
                    Log.i(Constants.TAG, "OnErrorResponse: $it")
                })
            requestQueue.add(detailsRequest)
        }
    }
}