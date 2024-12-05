package com.c242ps187.kidzlearnapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.c242ps187.kidzlearnapp.R
import com.c242ps187.kidzlearnapp.databinding.FragmentProfileBinding
import com.c242ps187.kidzlearnapp.view.activity.AboutActivity
import com.c242ps187.kidzlearnapp.view.activity.FeedbackActivity
import com.c242ps187.kidzlearnapp.view.viewmodel.MainViewModel
import com.c242ps187.kidzlearnapp.view.viewmodel.ViewModelFactory

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivAbout.setOnClickListener{
            startActivity(Intent(requireContext(), AboutActivity::class.java))
        }

        binding.ivFeedback.setOnClickListener{
            startActivity(Intent(requireContext(), FeedbackActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle(getString(R.string.alert))
                setMessage(getString(R.string.confirm_logout))
                setPositiveButton(getString(R.string.yes)) { _, _ ->
                    viewModel.logout()
                }
                setNeutralButton(getString(R.string.cancel)) { _, _ ->

                }
                create()
                show()
            }
        }
    }
}