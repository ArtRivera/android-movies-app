package com.artrivera.moviesapp.movie.domain

/**
 * Movie Repository interface that defines the methods to be implemented by the repository.
 * The repository is responsible for providing the data to the domain layer.
 * @author Arturo Rivera
 */
interface MovieRepository {
    /**
     * Returns a list of upcoming movies.
     */
    suspend fun getUpcomingMovies(): List<Movie>

    /**
     * Returns a list of popular movies.
     */
    suspend fun getPopularMovies(): List<Movie>

    /**
     * Returns a list of top rated movies.
     */
    suspend fun getTopRatedMovies(): List<Movie>

    /**
     * Returns a movie detail.
     */
    suspend fun getMovieDetail(movieId: Int): Movie
}