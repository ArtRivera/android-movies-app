package com.artrivera.moviesapp.movie.presentation.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.artrivera.moviesapp.R
import com.artrivera.moviesapp.databinding.FragmentMovieListBinding
import com.artrivera.moviesapp.movie.data.network.TheMovieDbClient
import com.artrivera.moviesapp.movie.data.network.TheMovieDbRemoteDataSource
import com.artrivera.moviesapp.movie.data.repository.MovieRepositoryImpl
import com.artrivera.moviesapp.movie.presentation.MoviesViewModel
import com.artrivera.moviesapp.movie.presentation.MoviesViewModelFactory
import com.artrivera.moviesapp.core.data.Result
import com.artrivera.moviesapp.core.data.database.AppDatabase
import com.artrivera.moviesapp.core.data.network.SimpleInternetChecker
import com.artrivera.moviesapp.core.presentation.BaseFragment
import com.artrivera.moviesapp.movie.domain.Movie
import com.artrivera.moviesapp.movie.domain.MovieSection
import com.artrivera.moviesapp.movie.presentation.movie_list.adapters.MovieSectionsAdapter
import com.artrivera.moviesapp.movie.presentation.movie_list.adapters.MoviesListAdapter

class MovieListFragment : BaseFragment(),
    MoviesListAdapter.MovieClickListener {

    private lateinit var binding: FragmentMovieListBinding
    private val viewModel by viewModels<MoviesViewModel> {
        MoviesViewModelFactory(
            MovieRepositoryImpl(
                TheMovieDbRemoteDataSource(TheMovieDbClient.instance),
                AppDatabase.getInstance(requireContext()).movieDao(),
                SimpleInternetChecker,
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)
        viewModel.getMovieSections().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success<*> -> {
                    binding.progressBar.visibility = View.GONE

                    val movieSections = result.data as List<MovieSection>
                    binding.rvMoviesSections.adapter = MovieSectionsAdapter(movieSections, this)
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

    override fun onMovieClick(movie: Movie) {
        val action =
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}