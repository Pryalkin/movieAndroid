package com.bsuir.moviesearchsystem.app.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.moviesearchsystem.Singletons
import com.bsuir.moviesearchsystem.app.dto.utils.HttpResponse
import com.bsuir.moviesearchsystem.app.dto.UserDTO
import com.bsuir.moviesearchsystem.app.repository.UserRepository
import com.bsuir.moviesearchsystem.app.utils.MutableLiveEvent
import com.bsuir.moviesearchsystem.app.utils.publishEvent
import com.bsuir.moviesearchsystem.app.utils.share
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Response

class RegistrationViewModel(
    private val userRepository: UserRepository = Singletons.userRepository
): ViewModel() {

    private val _message = MutableLiveEvent<String>()
    val message = _message.share()

    fun registration(userDTO: UserDTO){
        viewModelScope.launch {
            var res: Response<HttpResponse> = userRepository.registration(userDTO)
            if (res.isSuccessful){
                showAuthToast("Пользователь успешно зарегистрирован!")
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showAuthToast(mes)
            }
        }
    }

    private fun showAuthToast(mes: String) = _message.publishEvent(mes)

}