package com.artrivera.moviesapp.movie.presentation.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.artrivera.moviesapp.core.Result
import com.artrivera.moviesapp.movie.domain.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {


    fun getMovieDetail(movieId: Int) = liveData(Dispatchers.IO) {
        try {
            val movie = repository.getMovieDetail(movieId)
            emit(Result.Success(movie))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}

class MovieDetailViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}