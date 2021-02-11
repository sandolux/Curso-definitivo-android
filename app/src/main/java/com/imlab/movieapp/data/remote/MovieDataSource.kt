package com.imlab.movieapp.data.remote

import com.imlab.movieapp.application.AppConstans
import com.imlab.movieapp.data.model.Movies
import com.imlab.movieapp.interfaces.IWebService

class MovieDataSource ( private val webService : IWebService) {

     suspend fun getUpcomingMovies(): Movies = webService.getUpcomingMovies(AppConstans.API_KEY)

     suspend fun getTopRatedMovies(): Movies = webService.getTopRatedMovies(AppConstans.API_KEY)

     suspend fun getPopularMovies(): Movies = webService.getPopularMovies(AppConstans.API_KEY)

}