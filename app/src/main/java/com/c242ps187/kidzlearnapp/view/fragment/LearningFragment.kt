package com.c242ps187.kidzlearnapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.c242ps187.kidzlearnapp.databinding.FragmentLearningBinding
import com.c242ps187.kidzlearnapp.view.activity.LearningAnimalsActivity


class LearningFragment : Fragment() {
    private lateinit var binding: FragmentLearningBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAnimals.setOnClickListener{
            startActivity(Intent(requireContext(), LearningAnimalsActivity::class.java))
        }
    }
}