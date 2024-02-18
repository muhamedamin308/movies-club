package com.example.androidpractice1.ui.activities

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice1.R
import com.example.androidpractice1.databinding.LauncherScreenBinding

class LauncherActivity : AppCompatActivity() {
    private lateinit var launcherBinding: LauncherScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcherBinding = LauncherScreenBinding.inflate(layoutInflater)
        setContentView(launcherBinding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        launcherBinding.apply {
            imageView.animation =
                AnimationUtils.loadAnimation(this@LauncherActivity, R.anim.slide_up)
            button.setOnClickListener {
                val intent = Intent(this@LauncherActivity, LogInActivity::class.java)
                startActivity(
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(this@LauncherActivity).toBundle()
                )
            }
        }
    }
}