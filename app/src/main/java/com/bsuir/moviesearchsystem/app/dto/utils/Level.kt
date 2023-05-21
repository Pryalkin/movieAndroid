package com.bsuir.moviesearchsystem.app.dto.utils

enum class Level(private val displayValue: String) {
    FREE("FREE"),
    SUBSCRIPTION("SUBSCRIPTION");

    override fun toString(): String {
        return displayValue
    }
}