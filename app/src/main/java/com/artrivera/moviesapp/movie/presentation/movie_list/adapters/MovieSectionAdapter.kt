package com.artrivera.moviesapp.movie.presentation.movie_list.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artrivera.moviesapp.core.BaseViewHolder
import com.artrivera.moviesapp.databinding.SectionOfMoviesListBinding
import com.artrivera.moviesapp.movie.domain.Movie
import com.artrivera.moviesapp.movie.domain.MovieSection

class MovieSectionAdapter(private val movieSections: List<MovieSection>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val binding =
            SectionOfMoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieSectionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<*>,
        position: Int
    ) {
        when (holder) {
            is MovieSectionViewHolder -> holder.bind(movieSections[position])
        }
    }

    override fun getItemCount(): Int = movieSections.size

    private inner class MovieSectionViewHolder(val binding: SectionOfMoviesListBinding) :
        BaseViewHolder<MovieSection>(binding.root) {
        override fun bind(item: MovieSection) {
            binding.movieSectionTitle.text = item.sectionId
            val movieClickListener = object : MovieAdapter.MovieClickListener {
                override fun onMovieClick(movie: Movie) {
                    Log.d("Movie", "onMovieClick: $movie")
                }

            }
            val adapter = MovieAdapter(item.movies, movieClickListener)
            binding.rvMovies.adapter = adapter
        }
    }
}