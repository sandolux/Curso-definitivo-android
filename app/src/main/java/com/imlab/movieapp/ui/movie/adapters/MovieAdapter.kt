package com.imlab.movieapp.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imlab.movieapp.core.BaseViewHolder
import com.imlab.movieapp.data.model.Movie
import com.imlab.movieapp.databinding.MovieItemBinding

class MovieAdapter (private val movies :  List<Movie>,
                    private val itemClickListener: IOnMovieClickListener): RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface IOnMovieClickListener {
        fun onMovieClick(movie : Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(movies[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context) : BaseViewHolder<Movie>( binding.root ){

        override fun bind(item: Movie) {
            Glide.with( context ).load("https://image.tmdb.org/t/p/w500/${item.poster_path}").centerCrop().into(binding.imgMovie)
        }

    }
}