package com.c242ps187.kidzlearnapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.c242ps187.kidzlearnapp.databinding.FragmentExerciseBinding
import com.c242ps187.kidzlearnapp.view.activity.WritingActivity


class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.b1lv1.setOnClickListener{ startActivity(Intent(requireContext(), WritingActivity::class.java)) }
    }

}