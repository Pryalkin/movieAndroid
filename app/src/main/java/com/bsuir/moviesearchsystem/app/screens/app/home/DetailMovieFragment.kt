package com.bsuir.moviesearchsystem.app.screens.app.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bsuir.moviesearchsystem.R
import com.bsuir.moviesearchsystem.app.dto.MovieAnswerDTO
import com.bsuir.moviesearchsystem.app.views.HomeViewModel
import com.bsuir.moviesearchsystem.databinding.FragmentDetailMovieBinding
import com.bumptech.glide.Glide

class DetailMovieFragment : Fragment() {

    lateinit var binding: FragmentDetailMovieBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(inflater)
        val id: Long = requireArguments().getString(ARG_MOVIE_ID)!!.toLong()
        viewModel.getMovie(id)
        observeMovie()
        return binding.root
    }

    private fun observeMovie() = viewModel.movie.observe(viewLifecycleOwner) {
       binding.apply {
           tvMovie.text = it.name
           tvCountry.text = it.country
           tvGenre.text = it.genre
           tvLevel.text = it.level
           tvRat.text = it.ratings.toString()
           Glide.with(imageView.context)
               .load(R.drawable.ic_film)
               .into(imageView)
       }
    }

    companion object {
        private const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
        fun newInstance(movie: MovieAnswerDTO): DetailMovieFragment {
            val fragment = DetailMovieFragment()
            fragment.arguments = bundleOf(ARG_MOVIE_ID to movie.id.toString())
            return fragment
        }
    }
}