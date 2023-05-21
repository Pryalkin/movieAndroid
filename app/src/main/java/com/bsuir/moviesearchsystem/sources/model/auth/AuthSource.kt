package com.bsuir.moviesearchsystem.sources.model.auth

import com.bsuir.moviesearchsystem.app.dto.utils.HttpResponse
import com.bsuir.moviesearchsystem.app.dto.LoginUserAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.UserDTO
import retrofit2.Response

interface AuthSource {
    suspend fun registration(userDTO: UserDTO): Response<HttpResponse>
    suspend fun login(userDTO: UserDTO): Response<LoginUserAnswerDTO>
}