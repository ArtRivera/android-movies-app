package com.artrivera.moviesapp.movie.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.artrivera.moviesapp.core.Result
import com.artrivera.moviesapp.movie.domain.MovieRepository
import kotlinx.coroutines.Dispatchers

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getUpcomingMovies() = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val movies = repository.getUpcomingMovies()
            emit(Result.Success(movies))
            Log.d("MoviesViewModel", "Upcoming movies: $movies")
        } catch (e: Exception) {
            emit(Result.Error(e))
            Log.e("MoviesViewModel", "Error getting upcoming movies", e)
        }

    }
}


class MoviesViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}