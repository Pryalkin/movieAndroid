package com.bsuir.moviesearchsystem.app.repository

import com.bsuir.moviesearchsystem.app.dto.MovieAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.utils.HttpResponse
import com.bsuir.moviesearchsystem.app.dto.MovieDTO
import com.bsuir.moviesearchsystem.app.setting.AppSettings
import com.bsuir.moviesearchsystem.sources.exception.BackendException
import com.bsuir.moviesearchsystem.sources.exception.InvalidCredentialsException
import com.bsuir.moviesearchsystem.sources.model.home.HomeSource
import retrofit2.Response

class HomeRepository(
    private val homeSource: HomeSource,
    private val appSettings: AppSettings
) {

    fun getRole(): String?{
        return appSettings.getCurrentRole()
    }

    suspend fun registration(movieDTO: MovieDTO): Response<List<MovieAnswerDTO>> {
        val res: Response<List<MovieAnswerDTO>> = try {
            homeSource.registration(movieDTO)
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

    suspend fun getAllFilms(): Response<List<MovieAnswerDTO>> {
        val res: Response<List<MovieAnswerDTO>> = try {
            homeSource.getAllFilms()
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

   suspend fun getMovie(id: Long): Response<MovieAnswerDTO> {
        val res: Response<MovieAnswerDTO> = try {
            homeSource.getMovie(id)
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

    suspend fun getMoviesFind(find: String): Response<List<MovieAnswerDTO>> {
        val res: Response<List<MovieAnswerDTO>> = try {
            homeSource.getMoviesFind(find)
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
}