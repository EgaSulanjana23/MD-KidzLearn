package com.c242ps187.kidzlearnapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.c242ps187.kidzlearnapp.data.result.Result
import com.c242ps187.kidzlearnapp.databinding.ActivityLearningAnimalsBinding
import com.c242ps187.kidzlearnapp.utils.Utils.showLoading
import com.c242ps187.kidzlearnapp.view.viewmodel.MainViewModel
import com.c242ps187.kidzlearnapp.view.viewmodel.ViewModelFactory

class LearningAnimalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearningAnimalsBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    val adapter = AdapterLearningAnimals()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearningAnimalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, NavActivity::class.java).putExtra("FRAGMENT_TARGET", "learning"))
        }

        viewModel.getLearningAnimals().observe(this){ result ->
            when(result){
                is Result.Loading -> {binding.pbLearningAnimals.showLoading(true)}
                is Result.Success -> {
                    binding.pbLearningAnimals.showLoading(false)
                    adapter.submitList(result.data)
                    binding.rvAnimal.adapter = adapter
                    binding.rvAnimal.layoutManager = GridLayoutManager(this, 3)
                    Toast.makeText(this@LearningAnimalsActivity, "Fetch Data Success", Toast.LENGTH_SHORT).show()
                }
                is Result.Error -> {
                    binding.pbLearningAnimals.showLoading(false)
                    Log.d("Animals", result.error)
                    Toast.makeText(this@LearningAnimalsActivity, result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}