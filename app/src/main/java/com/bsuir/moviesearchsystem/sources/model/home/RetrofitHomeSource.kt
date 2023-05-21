package com.bsuir.moviesearchsystem.sources.model.home

import com.bsuir.moviesearchsystem.app.dto.MovieAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.utils.HttpResponse
import com.bsuir.moviesearchsystem.app.dto.MovieDTO
import com.bsuir.moviesearchsystem.sources.backend.BackendRetrofitSource
import com.bsuir.moviesearchsystem.sources.backend.RetrofitConfig
import kotlinx.coroutines.delay
import retrofit2.Response


class RetrofitHomeSource(
    config: RetrofitConfig
) : BackendRetrofitSource(config), HomeSource {

    private val homeApi = retrofit.create(HomeApi::class.java)

    override suspend fun registration(movieDTO: MovieDTO): Response<List<MovieAnswerDTO>> = wrapRetrofitExceptions {
        delay(1000)
        homeApi.registration(movieDTO)
    }

    override suspend fun getAllFilms(): Response<List<MovieAnswerDTO>>  = wrapRetrofitExceptions {
        delay(1000)
        homeApi.getAllFilms()
    }

    override suspend fun getMovie(id: Long): Response<MovieAnswerDTO> = wrapRetrofitExceptions {
        delay(1000)
        homeApi.getMovie(id)
    }

    override suspend fun getMoviesFind(find: String): Response<List<MovieAnswerDTO>> = wrapRetrofitExceptions {
        delay(1000)
        homeApi.getMoviesFind(find)
    }

}