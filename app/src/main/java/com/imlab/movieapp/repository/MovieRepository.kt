package com.imlab.movieapp.repository

import android.util.Log
import com.imlab.movieapp.data.model.Movies
import com.imlab.movieapp.data.remote.MovieDataSource
import com.imlab.movieapp.interfaces.IMovieRepository

class MovieRepository(private val dataSource: MovieDataSource) : IMovieRepository {

    override suspend fun getUpcomingMovies(): Movies {
        Log.d("LiveData", "MovieRepository passing ok")
        return dataSource.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): Movies = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): Movies = dataSource.getPopularMovies()
}