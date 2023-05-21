package com.bsuir.moviesearchsystem.sources.model.home

import com.bsuir.moviesearchsystem.app.dto.MovieAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.utils.HttpResponse
import com.bsuir.moviesearchsystem.app.dto.MovieDTO
import retrofit2.Response

interface HomeSource {
    suspend fun registration(movieDTO: MovieDTO): Response<List<MovieAnswerDTO>>
    suspend fun getAllFilms(): Response<List<MovieAnswerDTO>>
    suspend fun getMovie(id: Long): Response<MovieAnswerDTO>
    suspend fun getMoviesFind(find: String): Response<List<MovieAnswerDTO>>
}