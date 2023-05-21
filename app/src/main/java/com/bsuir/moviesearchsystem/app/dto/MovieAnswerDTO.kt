package com.bsuir.moviesearchsystem.app.dto

import java.util.*

data class MovieAnswerDTO(
    val id: Long,
    val name: String,
    val genre: String,
    val country: String,
    val level: String,
    val ratings: Double,
    val date: Date
)