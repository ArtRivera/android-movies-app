package com.artrivera.moviesapp.movie.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.artrivera.moviesapp.core.data.Result
import com.artrivera.moviesapp.movie.domain.MovieRepository
import com.artrivera.moviesapp.movie.domain.MovieSection
import kotlinx.coroutines.Dispatchers

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    val moviesSections = mutableListOf<MovieSection>()

    fun getMovieSections() = liveData(Dispatchers.IO) {
        emit(Result.Loading)

        if (moviesSections.isNotEmpty()) {
            emit(Result.Success(moviesSections))
            return@liveData
        }

        try {
            val upcomingMovies = repository.getUpcomingMovies()
            moviesSections.add(MovieSection("Upcoming", upcomingMovies))
            Log.d("MoviesViewModel", "Upcoming movies: $upcomingMovies")
        } catch (e: Exception) {
            Log.e("MoviesViewModel", "Error getting upcoming movies", e)
        }

        try {
            val topRatedMovies = repository.getTopRatedMovies()
            moviesSections.add(MovieSection("Top Rated", topRatedMovies))
            Log.d("MoviesViewModel", "Top rated movies: $topRatedMovies")
        } catch (e: Exception) {
            emit(Result.Error(e))
            Log.e("MoviesViewModel", "Error getting top rated movies", e)
        }

        try {
            val movies = repository.getPopularMovies()
            moviesSections.add(MovieSection("Popular", movies))
            Log.d("MoviesViewModel", "Popular movies: $movies")
        } catch (e: Exception) {
            Log.e("MoviesViewModel", "Error getting popular movies", e)
        }

        if (moviesSections.isEmpty()) {
            emit(Result.Error(Exception("No movies found")))
        } else {
            emit(Result.Success(moviesSections))
        }
    }
}


class MoviesViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repository)
    }
}