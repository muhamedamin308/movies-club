package com.example.androidpractice1.ui.activities

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice1.ExistedUsers
import com.example.androidpractice1.databinding.ActivityLogInBinding


class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        @Suppress("DEPRECATION") super.onBackPressed()
        finish()
        overridePendingTransition(0, android.R.anim.fade_out)
    }

    private fun initUI() {
        binding.apply {
            register.setOnClickListener {
                val intent = Intent(this@LogInActivity, RegisterActivity::class.java)
                startActivity(
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(this@LogInActivity).toBundle()
                )
            }
            login.setOnClickListener {
                if (emailInput.text.toString().isEmpty() || passwordInput.text.toString()
                        .isEmpty()
                ) {
                    Toast.makeText(
                        this@LogInActivity, "Please fill all the fields", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val user = ExistedUsers.getUsers(emailInput.text.toString())
                    user?.let {
                        val intent = Intent(this@LogInActivity, MoviesActivity::class.java)
                        startActivity(
                            intent,
                            ActivityOptions.makeSceneTransitionAnimation(this@LogInActivity)
                                .toBundle()
                        )
                        finish()
                    }
                    if (user == null) Toast.makeText(
                        this@LogInActivity, "Invalid credentials", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}