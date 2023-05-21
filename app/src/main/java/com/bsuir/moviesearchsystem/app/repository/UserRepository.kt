package com.bsuir.moviesearchsystem.app.repository

import com.bsuir.moviesearchsystem.app.dto.utils.HttpResponse
import com.bsuir.moviesearchsystem.app.dto.LoginUserAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.UserDTO
import com.bsuir.moviesearchsystem.app.setting.AppSettings
import com.bsuir.moviesearchsystem.sources.exception.BackendException
import com.bsuir.moviesearchsystem.sources.exception.InvalidCredentialsException
import com.bsuir.moviesearchsystem.sources.model.auth.AuthSource
import retrofit2.Response

class UserRepository(
    private val userSource: AuthSource,
    private val appSettings: AppSettings
) {

    suspend fun login(userDTO: UserDTO): Response<LoginUserAnswerDTO> {
        val res: Response<LoginUserAnswerDTO> = try {
            userSource.login(userDTO)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                // map 401 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }
        appSettings.setCurrentToken(res.headers().get("Jwt-Token"))
        appSettings.setCurrentUsername(res.body()?.username)
        appSettings.setCurrentRole(res.body()?.role)
        return res
    }

    suspend fun registration(userDTO: UserDTO): Response<HttpResponse> {
        val res: Response<HttpResponse> = try {
            userSource.registration(userDTO)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                // map 401 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }
        return res
    }

    fun logout(){
        appSettings.setCurrentToken("")
        appSettings.setCurrentUsername("")
        appSettings.setCurrentRole("")
    }

}