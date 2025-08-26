package com.artrivera.moviesapp.movie.presentation.movie_list.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.artrivera.moviesapp.core.presentation.BaseViewHolder
import com.artrivera.moviesapp.databinding.MovieListItemBinding
import com.artrivera.moviesapp.movie.domain.Movie
import com.bumptech.glide.Glide

class MoviesListAdapter(
    private val moviesList: List<Movie>,
    private val itemClickListener: MovieClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {


    interface MovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val itemBinding =
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MovieViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(moviesList[position])
        }

        return holder
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<*>,
        position: Int
    ) {
        when (holder) {
            is MovieViewHolder -> holder.bind(moviesList[position])
        }
    }

    override fun getItemCount(): Int = moviesList.size

    private inner class MovieViewHolder(val binding: MovieListItemBinding, val context: Context) :
        BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {
            Glide.with(context).load(item.posterPath)
                .centerCrop()
                .into(binding.movieImage)
        }

    }
}