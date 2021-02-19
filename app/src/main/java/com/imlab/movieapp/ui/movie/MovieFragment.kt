package com.imlab.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.imlab.movieapp.R
import com.imlab.movieapp.application.RetrofitClient
import com.imlab.movieapp.core.Resource
import com.imlab.movieapp.data.model.Movie
import com.imlab.movieapp.data.remote.MovieDataSource
import com.imlab.movieapp.databinding.FragmentMovieBinding
import com.imlab.movieapp.presentation.MovieViewModel
import com.imlab.movieapp.presentation.MovieViewModelFactory
import com.imlab.movieapp.repository.MovieRepository
import com.imlab.movieapp.ui.movie.adapters.MovieAdapter
import com.imlab.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.imlab.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.imlab.movieapp.ui.movie.adapters.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie),  MovieAdapter.IOnMovieClickListener {

    private lateinit var binding : FragmentMovieBinding
    private lateinit var concatAdapter: ConcatAdapter

    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(MovieRepository(MovieDataSource(RetrofitClient.webservice)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieBinding.bind( view )
        concatAdapter = ConcatAdapter()

        viewModel.fetchMainPageMovies().observe(viewLifecycleOwner, Observer { result->

            when ( result ) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MovieAdapter(result.data.first.results,this@MovieFragment)))
                        addAdapter(1, TopRatedConcatAdapter(MovieAdapter(result.data.second.results,this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(result.data.third.results,this@MovieFragment)))
                    }

                    binding.rvMovies.adapter = concatAdapter
                }

                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("LiveData","${ result.exception }")
                }
            }

        })

    }

    override fun onMovieClick(movie: Movie) {
        Log.d( "Movie", "onClickMovie: $movie " )

        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )

        findNavController().navigate(action)

    }


}