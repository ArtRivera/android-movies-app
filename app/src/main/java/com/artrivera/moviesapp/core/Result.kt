package com.artrivera.moviesapp.core

sealed class Result {
    object Loading : Result()

    data class Success<out T>(val data: T) : Result()

    data class Error(val exception: Exception) : Result()

}