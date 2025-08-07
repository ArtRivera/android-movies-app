package com.artrivera.moviesapp.movie.domain

/**
 * Movie Repository interface that defines the methods to be implemented by the repository.
 * The repository is responsible for providing the data to the domain layer.
 * @author Arturo Rivera
 */
interface MovieRepository {
    suspend fun getUpcomingMovies(): List<Movie>
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getTopRatedMovies(): List<Movie>
}