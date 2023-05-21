package com.bsuir.moviesearchsystem.app.screens.app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bsuir.moviesearchsystem.R
import com.bsuir.moviesearchsystem.app.screens.auth.MainActivity
import com.bsuir.moviesearchsystem.app.views.LoginViewModel
import com.bsuir.moviesearchsystem.app.views.LogoutViewModel
import com.bsuir.moviesearchsystem.databinding.FragmentLogoutBinding

class LogoutFragment : Fragment() {

    private lateinit var binding: FragmentLogoutBinding
    private val viewModel by viewModels<LogoutViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLogoutBinding.inflate(inflater)
        viewModel.logout()
        Toast.makeText(activity, "Вы успешно покинули приложение!", Toast.LENGTH_SHORT).show()
        requireActivity().startActivity(Intent(activity, MainActivity::class.java))
        return binding.root
    }

}