package com.imlab.movieapp.repository

import com.imlab.movieapp.data.model.Movies
import com.imlab.movieapp.data.remote.MovieDataSource
import com.imlab.movieapp.interfaces.IMovieRespository

class MovieRepository(private val dataSource: MovieDataSource) : IMovieRespository {

    override suspend fun getUpcomingMovies(): Movies = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): Movies = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): Movies = dataSource.getPopularMovies()
}