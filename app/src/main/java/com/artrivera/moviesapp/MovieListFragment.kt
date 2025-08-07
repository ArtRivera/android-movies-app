package com.artrivera.moviesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.artrivera.moviesapp.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private lateinit var binding: FragmentMovieListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)
    }
}