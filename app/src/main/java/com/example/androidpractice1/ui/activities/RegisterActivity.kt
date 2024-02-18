package com.example.androidpractice1.ui.activities

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice1.ExistedUsers
import com.example.androidpractice1.Users
import com.example.androidpractice1.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        @Suppress("DEPRECATION") super.onBackPressed()
        finish()
        overridePendingTransition(0, android.R.anim.fade_out)
    }

    private fun checkUserInput(): Boolean =
        (binding.passwordInput.text.toString().isEmpty() || binding.emailInput.text.toString()
            .isEmpty() || binding.nameInput.text.toString().isEmpty())

    private fun initUI() {
        binding.apply {
            signup.setOnClickListener {
                if (checkUserInput()) {
                    Toast.makeText(
                        this@RegisterActivity, "Please fill empty fields", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    ExistedUsers.addUser(
                        emailInput.text.toString(),
                        Users(emailInput.text.toString(), passwordInput.text.toString().toInt())
                    )
                    val intent = Intent(this@RegisterActivity, MoviesActivity::class.java)
                    startActivity(
                        intent,
                        ActivityOptions.makeSceneTransitionAnimation(this@RegisterActivity)
                            .toBundle()
                    )
                    finish()
                }
            }
        }
    }
}