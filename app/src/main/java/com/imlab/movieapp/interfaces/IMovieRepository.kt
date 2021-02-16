package com.imlab.movieapp.interfaces

import com.imlab.movieapp.data.model.Movies

interface IMovieRepository {
    suspend fun getUpcomingMovies() : Movies
    suspend fun getTopRatedMovies() :  Movies
    suspend fun getPopularMovies() :  Movies
}