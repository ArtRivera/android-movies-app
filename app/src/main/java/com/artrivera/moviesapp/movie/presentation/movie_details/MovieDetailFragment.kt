package com.artrivera.moviesapp.movie.presentation.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.artrivera.moviesapp.R
import com.artrivera.moviesapp.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)

    }
}