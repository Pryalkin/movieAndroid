package com.bsuir.moviesearchsystem.sources.model.auth

import com.bsuir.moviesearchsystem.app.dto.utils.HttpResponse
import com.bsuir.moviesearchsystem.app.dto.LoginUserAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("user/registration")
    suspend fun registration(@Body userDTO: UserDTO): Response<HttpResponse>

    @POST("user/login")
    suspend fun login(@Body userDTO: UserDTO): Response<LoginUserAnswerDTO>

}