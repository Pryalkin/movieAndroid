package com.bsuir.moviesearchsystem.app.views

import androidx.lifecycle.ViewModel
import com.bsuir.moviesearchsystem.Singletons
import com.bsuir.moviesearchsystem.app.repository.UserRepository

class LogoutViewModel(
    private val userRepository: UserRepository = Singletons.userRepository
): ViewModel() {

    fun logout(){
        userRepository.logout();
    }

}