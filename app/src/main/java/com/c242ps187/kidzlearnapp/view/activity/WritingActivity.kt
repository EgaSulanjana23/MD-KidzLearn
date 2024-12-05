package com.c242ps187.kidzlearnapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.c242ps187.kidzlearnapp.databinding.ActivityWritingBinding
import com.c242ps187.kidzlearnapp.view.viewmodel.MainViewModel
import com.c242ps187.kidzlearnapp.view.viewmodel.ViewModelFactory

class WritingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWritingBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWritingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawingView = binding.canvasView

        Glide.with(this)
            .load("https://storage.googleapis.com/kidzlearn-bucket/alphabetAnimation/images/A_K.png")
            .into(binding.imageView)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, NavActivity::class.java))
        }

        binding.btnDelete.setOnClickListener{
            drawingView.clearCanvas()
        }
    }
}