package com.bsuir.moviesearchsystem.app.dto

data class LoginUserAnswerDTO(
    val username: String,
    val role: String,
    val authorities: Array<String>
)
