package com.artrivera.moviesapp.movie.domain

data class Movie(
    val id: Int,
    val title: String,
    val posterId: String,
    val backdropImageId: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val onlyForAdults: Boolean,
    val genres: List<MovieGenre>,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
) {
    val posterPath: String
        get() = "https://image.tmdb.org/t/p/w500/$posterId"

    val backdropPath: String
        get() = "https://image.tmdb.org/t/p/w500/$backdropImageId"
}

