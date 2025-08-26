package com.artrivera.moviesapp.core.domain

interface InternetChecker {

    suspend fun isInternetAvailable(): Boolean
}