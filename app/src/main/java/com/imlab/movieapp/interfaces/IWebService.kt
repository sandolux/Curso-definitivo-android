package com.imlab.movieapp.interfaces

import com.imlab.movieapp.data.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface IWebService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey : String) : Movies
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey : String) : Movies
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String) : Movies
}

