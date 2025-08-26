package com.artrivera.moviesapp.movie.data.repository

import com.artrivera.moviesapp.movie.data.database.MovieDao
import com.artrivera.moviesapp.movie.data.database.MovieEntityType
import com.artrivera.moviesapp.movie.data.mappers.toMovie
import com.artrivera.moviesapp.movie.data.mappers.toMovieEntity
import com.artrivera.moviesapp.movie.data.network.TheMovieDbRemoteDataSource
import com.artrivera.moviesapp.movie.domain.Movie
import com.artrivera.moviesapp.movie.domain.MovieRepository

class MovieRepositoryImpl(
    private val remoteDataSource: TheMovieDbRemoteDataSource,
    private val moviesDao: MovieDao
) :
    MovieRepository {

    override suspend fun getUpcomingMovies(): List<Movie> {
        val remoteMovies = remoteDataSource.getUpcomingMovies().results.map { it.toMovie() }
        remoteMovies.forEach { movie ->
            moviesDao.insertMovie(movie.toMovieEntity(MovieEntityType.UPCOMING))
        }
        return moviesDao.getMoviesBy(MovieEntityType.UPCOMING).map { it.toMovie() }
    }


    override suspend fun getPopularMovies(): List<Movie> {
        val remoteMovies = remoteDataSource.getPopularMovies().results.map { it.toMovie() }
        remoteMovies.forEach { movie ->
            moviesDao.insertMovie(movie.toMovieEntity(MovieEntityType.POPULAR))
        }
        return moviesDao.getMoviesBy(MovieEntityType.POPULAR).map { it.toMovie() }
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        val remoteMovies = remoteDataSource.getTopRatedMovies().results.map { it.toMovie() }
        remoteMovies.forEach { movie ->
            moviesDao.insertMovie(movie.toMovieEntity(MovieEntityType.TOP_RATED))
        }
        return moviesDao.getMoviesBy(MovieEntityType.TOP_RATED).map { it.toMovie() }
    }

    override suspend fun getMovieDetail(movieId: Int): Movie =
        remoteDataSource.getMovieDetail(movieId).toMovie()
}
