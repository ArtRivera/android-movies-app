package com.artrivera.moviesapp.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto (
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Int,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)