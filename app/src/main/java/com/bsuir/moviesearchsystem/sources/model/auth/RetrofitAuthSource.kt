package com.bsuir.moviesearchsystem.sources.model.auth

import com.bsuir.moviesearchsystem.app.dto.utils.HttpResponse
import com.bsuir.moviesearchsystem.app.dto.LoginUserAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.UserDTO
import com.bsuir.moviesearchsystem.sources.backend.BackendRetrofitSource
import com.bsuir.moviesearchsystem.sources.backend.RetrofitConfig
import kotlinx.coroutines.delay
import retrofit2.Response

class RetrofitAuthSource(
    config: RetrofitConfig
) : BackendRetrofitSource(config), AuthSource {

    private val userApi = retrofit.create(AuthApi::class.java)

    override suspend fun registration(userDTO: UserDTO): Response<HttpResponse> = wrapRetrofitExceptions {
        delay(1000)
        userApi.registration(userDTO)
    }

    override suspend fun login(userDTO: UserDTO): Response<LoginUserAnswerDTO>  = wrapRetrofitExceptions {
        delay(1000)
        userApi.login(userDTO)
    }


}