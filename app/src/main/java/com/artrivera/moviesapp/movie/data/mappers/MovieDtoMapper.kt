package com.artrivera.moviesapp.movie.data.mappers

import com.artrivera.moviesapp.movie.data.database.MovieEntity
import com.artrivera.moviesapp.movie.data.database.MovieEntityType
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

/**
 * Maps a [MovieDetailDto] to a [Movie]
 */
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

/**
 * Maps a [MovieEntity] to a [Movie]
 */
fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterId = posterId,
        backdropImageId = backdropImageId,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount,
        onlyForAdults = onlyForAdults,
        genres = emptyList(),
    )
}

/**
 * Maps a [Movie] to a [MovieEntity]
 */
fun Movie.toMovieEntity(movieType: MovieEntityType): MovieEntity{
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterId = posterId,
        backdropImageId = backdropImageId,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount,
        onlyForAdults = onlyForAdults,
        movieType = movieType.name
    )
}