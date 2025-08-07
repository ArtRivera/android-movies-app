package com.artrivera.moviesapp.movie.data.network

import com.artrivera.moviesapp.movie.data.dto.MoviesResultDto
import retrofit2.http.GET


/**
 * Service interface for The Movie Database API.
 */
interface TheMovieDbService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZTA5MDU2MjlmM2M0MjVhMDM3MDhjY2ZmZTkxYWMyZiIsIm5iZiI6MTU5NzA4MDE0My4wODA5OTk5LCJzdWIiOiI1ZjMxODI0ZmZjYWRiNDAwMzdlNThkMmUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.amE19L5aVfwHyanR6y4IIcF-TrUb0jaBZIgXi9evOrs"
    }

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MoviesResultDto

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResultDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MoviesResultDto


}