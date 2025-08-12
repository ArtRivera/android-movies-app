package com.artrivera.moviesapp.movie.presentation.movie_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.artrivera.moviesapp.R
import com.artrivera.moviesapp.databinding.FragmentMovieDetailBinding
import com.artrivera.moviesapp.movie.data.network.TheMovieDbClient
import com.artrivera.moviesapp.movie.data.network.TheMovieDbRemoteDataSource
import com.artrivera.moviesapp.movie.data.repository.MovieRepositoryImpl
import com.artrivera.moviesapp.movie.domain.Movie
import com.artrivera.moviesapp.core.Result
import com.artrivera.moviesapp.core.presentation.BaseFragment
import com.bumptech.glide.Glide
import kotlin.getValue

class MovieDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val viewModel by viewModels<MovieDetailViewModel> {
        MovieDetailViewModelFactory(
            MovieRepositoryImpl(TheMovieDbRemoteDataSource(TheMovieDbClient.instance))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set a listener to consume the insets
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            // The insets are consumed here, preventing them from being applied
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(
                left = systemBars.left,
                right = systemBars.right,
                bottom = systemBars.bottom
            )
            // Consume the top insets
            WindowInsetsCompat.CONSUMED
        }

        binding = FragmentMovieDetailBinding.bind(view)
        viewModel.getMovieDetail(args.movieId).observe(viewLifecycleOwner, { result ->


            when (result) {
                is Result.Error -> {
                    Log.d("MovieDetailFragment", "Error: ${result.exception}")
                }

                is Result.Loading -> {
                    Log.d("MovieDetailFragment", "Loading...")
                }

                is Result.Success<*> -> {
                    val movie = result.data as Movie
                    Glide.with(view).load(movie.posterPath).into(binding.posterMovieImage)
                    Glide.with(view).load(movie.backdropPath).into(binding.backdropMovieImage)
                    binding.textView.text = movie.title
                    binding.txtOverviewDescription.text = movie.overview
                    binding.txtLanguage.text = "Language: ${movie.originalLanguage}"
                    binding.txtReleaseDate.text = "Released on ${movie.releaseDate}"
                    binding.txtReviews.text = "${movie.voteAverage} (${movie.voteCount} reviews)"
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear the listener to avoid memory leaks
        ViewCompat.setOnApplyWindowInsetsListener(requireView(), null)
    }
}