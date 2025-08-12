package com.artrivera.moviesapp.movie.data.mappers

import com.artrivera.moviesapp.movie.data.dto.MovieDetailDto
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
        posterId = posterPath,
        backdropImageId = backdropPath,
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

fun MovieDetailDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterId = posterPath,
        backdropImageId = backdropPath,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount,
        onlyForAdults = adult,
        genres = genres.map { MovieGenre(it.id) }
    )
}