package com.example.androidpractice1.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.androidpractice1.Constants
import com.example.androidpractice1.R
import com.example.androidpractice1.adapter.CategoriesAdapter
import com.example.androidpractice1.adapter.FilmAdapter
import com.example.androidpractice1.adapter.SliderAdapter
import com.example.androidpractice1.databinding.ActivityMoviesBinding
import com.example.androidpractice1.domain.CategoryItem
import com.example.androidpractice1.domain.FilmItem
import com.example.androidpractice1.domain.SliderItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.abs


@Suppress("DEPRECATION")
class MoviesActivity : AppCompatActivity() {
    private var handler = Handler()
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var trendingMoviesAdapter: RecyclerView.Adapter<*>
    private lateinit var upComingAdapter: RecyclerView.Adapter<*>
    private lateinit var categoryAdapter: RecyclerView.Adapter<*>
    private lateinit var requestQueue: RequestQueue
    private lateinit var requestTrendingString: StringRequest
    private lateinit var requestUpComingString: StringRequest
    private lateinit var requestCategoryString: StringRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        banners()
        initCategoriesRequest()
        initTrendingMoviesRequest()
        initUpComingRequest()
    }

    private fun banners() {
        val sliderItems = ArrayList<SliderItems>()
        sliderItems.addAll(
            listOf(
                SliderItems(R.drawable.wide1),
                SliderItems(R.drawable.wide2),
                SliderItems(R.drawable.wide3),
                SliderItems(R.drawable.wide4),
                SliderItems(R.drawable.wide_image_poster)
            )
        )
        binding.viewPager2.apply {
            adapter = SliderAdapter(sliderItems, this)
            clipToPadding = false
            offscreenPageLimit = 5
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
        }
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { view, position ->
                val positivePosition = abs(position)
                view.scaleY = 1f + positivePosition * 0.15f
            }
        }
        binding.viewPager2.apply {
            setPageTransformer(compositePageTransformer)
            currentItem = 1
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handler.removeCallbacks(slider)
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(slider)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(slider, 2000)
    }

    private val slider = Runnable {
        run {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
        }
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
        finish()
    }

    private fun initUI() {
        binding.apply {
            categoryRec.layoutManager =
                LinearLayoutManager(this@MoviesActivity, LinearLayoutManager.HORIZONTAL, false)
            trendingRec.layoutManager =
                LinearLayoutManager(this@MoviesActivity, LinearLayoutManager.HORIZONTAL, false)
            upComingRec.layoutManager =
                LinearLayoutManager(this@MoviesActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun initTrendingMoviesRequest() {
        requestQueue = Volley.newRequestQueue(this)
        binding.apply {
            trendingProgressBar.visibility = View.VISIBLE
            requestTrendingString =
                StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", {
                    val gson = Gson()
                    trendingProgressBar.visibility = View.GONE
                    val films: FilmItem = gson.fromJson(it, FilmItem::class.java)
                    trendingMoviesAdapter = FilmAdapter(films)
                    trendingRec.adapter = trendingMoviesAdapter
                }, {
                    trendingProgressBar.visibility = View.GONE
                    Log.i(Constants.TAG, "OnErrorResponse: $it")
                })
            requestQueue.add(requestTrendingString)
        }
    }

    private fun initUpComingRequest() {
        requestQueue = Volley.newRequestQueue(this)
        binding.apply {
            upComingProgressBar.visibility = View.VISIBLE
            requestUpComingString =
                StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2", {
                    val gson = Gson()
                    upComingProgressBar.visibility = View.GONE
                    val films: FilmItem = gson.fromJson(it, FilmItem::class.java)
                    upComingAdapter = FilmAdapter(films)
                    upComingRec.adapter = upComingAdapter
                }, {
                    upComingProgressBar.visibility = View.GONE
                    Log.i(Constants.TAG, "OnErrorResponse: $it")
                })
            requestQueue.add(requestUpComingString)
        }
    }

    private fun initCategoriesRequest() {
        requestQueue = Volley.newRequestQueue(this)
        binding.apply {
            categoryProgressBar.visibility = View.VISIBLE
            requestCategoryString =
                StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/genres", {
                    val gson = Gson()
                    categoryProgressBar.visibility = View.GONE
                    val categories: ArrayList<CategoryItem> = gson.fromJson(
                        it, object : TypeToken<ArrayList<CategoryItem?>?>() {}.type
                    )
                    categoryAdapter = CategoriesAdapter(categories)
                    categoryRec.adapter = categoryAdapter
                }, {
                    categoryProgressBar.visibility = View.GONE
                    Log.i(Constants.TAG, "OnErrorResponse: $it")
                })
            requestQueue.add(requestCategoryString)
        }
    }
}