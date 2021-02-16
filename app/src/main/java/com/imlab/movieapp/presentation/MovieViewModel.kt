package com.imlab.movieapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.imlab.movieapp.core.Resource
import com.imlab.movieapp.interfaces.IMovieRepository
import com.imlab.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    fun fetchUpcomingMovies() = liveData(Dispatchers.IO) {
        Log.d("LiveData","MovieViewModel fetchUpcomingMovies")
        emit( Resource.Loading() )
        try{
            emit( Resource.Success( repo.getUpcomingMovies()))
        }catch ( e:  Exception ){
            Log.d("LiveData","MovieViewModel fail")
            emit( Resource.Failure(e) )
        }
    }

}

class MovieViewModelFactory(private val repo: IMovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IMovieRepository::class.java).newInstance( repo )
    }
}