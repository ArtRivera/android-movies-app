package com.artrivera.moviesapp.movie.data.repository

import com.artrivera.moviesapp.movie.data.mappers.toMovie
import com.artrivera.moviesapp.movie.data.network.TheMovieDbRemoteDataSource
import com.artrivera.moviesapp.movie.domain.Movie
import com.artrivera.moviesapp.movie.domain.MovieRepository

class MovieRepositoryImpl(private val remoteDataSource: TheMovieDbRemoteDataSource) :
    MovieRepository {
    override suspend fun getUpcomingMovies(): List<Movie> =
        remoteDataSource.getUpcomingMovies().results.map { it.toMovie() }


    override suspend fun getPopularMovies(): List<Movie> =
        remoteDataSource.getPopularMovies().results.map { it.toMovie() }

    override suspend fun getTopRatedMovies(): List<Movie> =
        remoteDataSource.getTopRatedMovies().results.map { it.toMovie() }
}
