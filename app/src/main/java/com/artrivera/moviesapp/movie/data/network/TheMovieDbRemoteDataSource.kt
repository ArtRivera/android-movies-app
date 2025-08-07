package com.artrivera.moviesapp.movie.data.network

import com.artrivera.moviesapp.movie.data.dto.MoviesResultDto

class TheMovieDbRemoteDataSource(private val service: TheMovieDbService) {

    suspend fun getUpcomingMovies(): MoviesResultDto = service.getUpcomingMovies()

    suspend fun getPopularMovies(): MoviesResultDto = service.getPopularMovies()

    suspend fun getTopRatedMovies(): MoviesResultDto = service.getTopRatedMovies()
}