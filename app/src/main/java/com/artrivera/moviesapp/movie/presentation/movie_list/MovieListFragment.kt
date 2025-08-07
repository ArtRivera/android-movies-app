package com.artrivera.moviesapp.movie.presentation.movie_list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.artrivera.moviesapp.R
import com.artrivera.moviesapp.databinding.FragmentMovieListBinding
import com.artrivera.moviesapp.movie.data.network.TheMovieDbClient
import com.artrivera.moviesapp.movie.data.network.TheMovieDbRemoteDataSource
import com.artrivera.moviesapp.movie.data.repository.MovieRepositoryImpl
import com.artrivera.moviesapp.movie.presentation.MoviesViewModel
import com.artrivera.moviesapp.movie.presentation.MoviesViewModelFactory
import com.artrivera.moviesapp.core.Result

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private lateinit var binding: FragmentMovieListBinding
    private val viewModel by viewModels<MoviesViewModel> {
        MoviesViewModelFactory(
            MovieRepositoryImpl(TheMovieDbRemoteDataSource(TheMovieDbClient.instance))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)
        viewModel.getUpcomingMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success<*> -> {
                    binding.progressBar.visibility = View.GONE
//                    binding.rvMovies.adapter = MoviesAdapter(result.data)
                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error: ${result.exception}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}