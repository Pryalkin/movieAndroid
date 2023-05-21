package com.bsuir.moviesearchsystem.app.dto.utils

enum class Genre(private val displayValue: String,
                 private val code: Int) {
    ACTION("ACTION", 1),
    ADVENTURES("ADVENTURES", 2),
    DRAMA("DRAMA", 3),
    COMEDY("COMEDY", 4),
    FANTASTIC("FANTASTIC", 5);

    fun getCode(): Int = code

    override fun toString(): String {
        return displayValue
    }
}