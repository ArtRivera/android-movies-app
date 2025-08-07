package com.artrivera.moviesapp.movie.data.mappers

import com.artrivera.moviesapp.movie.data.dto.MovieDto
import com.artrivera.moviesapp.movie.domain.Movie
import com.artrivera.moviesapp.movie.domain.MovieGenre

/**
 * Maps a [MovieDto] to a [Movie]
 */
fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterImagePath = posterPath,
        backdropImagePath = backdropPath,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount,
        onlyForAdults = adult,
        genres = genreIds.map { MovieGenre(it) }
    )
}