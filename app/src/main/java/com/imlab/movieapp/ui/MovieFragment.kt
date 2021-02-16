package com.imlab.movieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.imlab.movieapp.R
import com.imlab.movieapp.application.RetrofitClient
import com.imlab.movieapp.data.remote.MovieDataSource
import com.imlab.movieapp.databinding.FragmentMovieBinding
import com.imlab.movieapp.presentation.MovieViewModel
import com.imlab.movieapp.presentation.MovieViewModelFactory
import com.imlab.movieapp.repository.MovieRepository


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding : FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(MovieRepository(MovieDataSource(RetrofitClient.webservice)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieBinding.bind( view )

        Log.d("LiveData","Antes retrofit")

        viewModel.fetchUpcomingMovies().observe(viewLifecycleOwner, Observer {
            Log.d("LiveData", "en el observe $it")
            //REMOVI EL RESTO DEL CODIGO YA QUE NO HACE DIFERENCIA. DESDE ESTE PUNTO
            // EL PROGRAMA YA NO CARGA
        })

        Log.d("LiveData","Despues retrofit")


    }


}