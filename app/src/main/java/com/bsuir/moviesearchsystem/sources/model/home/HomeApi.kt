package com.bsuir.moviesearchsystem.sources.model.home

import com.bsuir.moviesearchsystem.app.dto.MovieAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.MovieDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeApi {

    @POST("movie/registration")
    suspend fun registration(@Body movieDTO: MovieDTO): Response<List<MovieAnswerDTO>>

    @GET("movie/getAll")
    suspend fun getAllFilms(): Response<List<MovieAnswerDTO>>

    @GET("movie/get")
    suspend fun getMovie(@Query("id") id: Long): Response<MovieAnswerDTO>

    @GET("movie/getFind")
    suspend fun getMoviesFind(@Query("find") find: String): Response<List<MovieAnswerDTO>>

}