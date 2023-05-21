package com.bsuir.moviesearchsystem.app.screens.app.home

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bsuir.moviesearchsystem.R
import com.bsuir.moviesearchsystem.app.dto.MovieAnswerDTO
import com.bsuir.moviesearchsystem.databinding.ItemFilmBinding
import com.bumptech.glide.Glide

interface MovieActionListener {
    fun onMovieDetails(movie: MovieAnswerDTO)
    fun onMovieSubscription(movie: MovieAnswerDTO)
}

class EmployeeDiffCallback(
    private val oldList: List<MovieAnswerDTO>,
    private val newList: List<MovieAnswerDTO>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldList[oldItemPosition]
        val newMovie= newList[newItemPosition]
        return oldMovie.id == newMovie.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldList[oldItemPosition]
        val newMovie = newList[newItemPosition]
        return oldMovie == newMovie
    }
}

class FilmsAdapter(
    private val actionListener: MovieActionListener
) : RecyclerView.Adapter<FilmsAdapter.MovieViewHolder>(), View.OnClickListener {

    class MovieViewHolder(val binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root)

    var moviesAnswerDTO: List<MovieAnswerDTO> = emptyList()
        set(newValue) {
            val diffCallback = EmployeeDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.moreImageViewButton.setOnClickListener(this)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesAnswerDTO[position]
        val context = holder.itemView.context
        with(holder.binding) {
            holder.itemView.tag = movie
            moreImageViewButton.tag = movie
            tvNameFilm.text = movie.name
            tvCountryFilm.text = movie.country
            tvGenreFilm.text = movie.genre
            tvLevelFilm.text = movie.level
            tvRating.text = movie.ratings.toString()
            Glide.with(imageView.context)
                .load(R.drawable.ic_film)
                .into(imageView)
        }
    }

    override fun getItemCount(): Int = moviesAnswerDTO.size

    override fun onClick(v: View) {
        val movie = v.tag as MovieAnswerDTO
        when (v.id){
            R.id.moreImageViewButton -> {
                showPopupMenu(v)
            }
            else -> {
                actionListener.onMovieDetails(movie)
            }
        }
    }

    private fun showPopupMenu(v: View) {
        val popupMenu = PopupMenu(v.context, v)
        val context = v.context
        val movie = v.tag as MovieAnswerDTO
        popupMenu.menu.add(0, ADD_SUBSCRIPTION, Menu.NONE, context.getString(R.string.add_subscription))
        popupMenu.setOnMenuItemClickListener{
            when (it.itemId){
                ADD_SUBSCRIPTION -> {
                    actionListener.onMovieSubscription(movie)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    companion object{
        private const val ADD_SUBSCRIPTION = 1
    }

}