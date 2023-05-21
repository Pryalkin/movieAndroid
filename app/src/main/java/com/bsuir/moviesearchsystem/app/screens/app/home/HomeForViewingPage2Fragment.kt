package com.bsuir.moviesearchsystem.app.screens.app.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsuir.moviesearchsystem.R
import com.bsuir.moviesearchsystem.Singletons.navigator
import com.bsuir.moviesearchsystem.app.dto.MovieAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.MovieDTO
import com.bsuir.moviesearchsystem.app.dto.utils.Genre
import com.bsuir.moviesearchsystem.app.dto.utils.Level
import com.bsuir.moviesearchsystem.app.utils.observeEvent
import com.bsuir.moviesearchsystem.app.views.HomeViewModel
import com.bsuir.moviesearchsystem.databinding.FragmentHomeForViewingPage2Binding
import kotlin.properties.Delegates

class HomeForViewingPage2Fragment : Fragment() {

    private var pageNumber by Delegates.notNull<Int>()
    private lateinit var binding: FragmentHomeForViewingPage2Binding
    private val viewModel by viewModels<HomeViewModel>()
    lateinit var adapterFilms: FilmsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = if (arguments != null) requireArguments().getInt("num") else 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeForViewingPage2Binding.inflate(inflater)

        when (pageNumber) {
            0 -> {
                customizeScreen(b = true, b1 = false)
                configureTheAdapterForTheFilms()
                customizeButton()
                binding.btFind.setOnClickListener {
                    binding.apply {
                        btFilter.visibility = View.INVISIBLE
                        etFind.visibility = View.VISIBLE
                        btCansellFind.visibility = View.VISIBLE
                        btFind.visibility = View.INVISIBLE
                        btContry.visibility = View.INVISIBLE
                        btGenre.visibility = View.INVISIBLE
                        btYears.visibility = View.INVISIBLE
                        btCansellFilter.visibility = View.INVISIBLE
                        btFind2.visibility = View.VISIBLE
                    }
                }
                binding.btCansellFind.setOnClickListener {
                    binding.apply {
                        customizeButton()
                    }
                }
                binding.btFilter.setOnClickListener {
                    binding.apply {
                        btFilter.visibility = View.INVISIBLE
                        etFind.visibility = View.INVISIBLE
                        btCansellFind.visibility = View.INVISIBLE
                        btFind.visibility = View.INVISIBLE
                        btContry.visibility = View.VISIBLE
                        btGenre.visibility = View.VISIBLE
                        btYears.visibility = View.VISIBLE
                        btCansellFilter.visibility = View.VISIBLE
                        btFind2.visibility = View.INVISIBLE
                    }
                }
                binding.btFind2.setOnClickListener {
                    val find: String = binding.etFind.text.toString()
                    viewModel.getMoviesFind(find)
                    binding.etFind.setText("")
                }
                binding.btCansellFilter.setOnClickListener {
                    customizeButton()
                }
                binding.btGenre.setOnClickListener {
                    showPopupMenu()
                }
            }
            1 -> {
                customizeScreen(b = false, b1 = true)
                seeSpinnerForLevel()
                seeSpinnerForGenre()
                binding.apply {
                    btnSend.setOnClickListener {
                        if(isValidMovieDTO()){
                            val movieDTO = MovieDTO(
                                name = edFilmName.text.toString(),
                                genre = spinnerGenre.selectedItem.toString(),
                                country = edCountry.text.toString(),
                                level = spinnerLevel.selectedItem.toString()
                            )
                            viewModel.registration(movieDTO)
                            edFilmName.setText("")
                            edCountry.setText("")
                        }
                    }
                }

            }
        }
        observeShowMessageEvent()
        return binding.root
    }

    private fun customizeButton() {
        binding.apply {
            etFind.visibility = View.INVISIBLE
            btFilter.visibility = View.VISIBLE
            btFind.visibility = View.VISIBLE
            btCansellFilter.visibility = View.INVISIBLE
            btCansellFind.visibility = View.INVISIBLE
            btContry.visibility = View.INVISIBLE
            btGenre.visibility = View.INVISIBLE
            btYears.visibility = View.INVISIBLE
            btFind2.visibility = View.INVISIBLE
        }
    }

    private fun configureTheAdapterForTheFilms() {
        val adapterFilms = FilmsAdapter(object  : MovieActionListener {
            override fun onMovieDetails(movie: MovieAnswerDTO) {
                navigator().showDetailMovie(movie)
            }
            override fun onMovieSubscription(movie: MovieAnswerDTO) {
                TODO("Not yet implemented")
            }
        })

        viewModel.movies.observe(viewLifecycleOwner){
            adapterFilms.moviesAnswerDTO = it
        }

        val layoutManagerAnnouncement = LinearLayoutManager(context)
        binding.recyclerViewAnnouncement.layoutManager = layoutManagerAnnouncement
        binding.recyclerViewAnnouncement.adapter = adapterFilms

        val itemAnimatorAnnouncement = binding.recyclerViewAnnouncement.itemAnimator
        if (itemAnimatorAnnouncement is DefaultItemAnimator){
            itemAnimatorAnnouncement.supportsChangeAnimations = false
        }
        viewModel.getAllFilms()
    }

    private fun observeShowMessageEvent() = viewModel.message.observeEvent(viewLifecycleOwner) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private fun seeSpinnerForLevel() {
        val adapter: ArrayAdapter<Level> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            Level.values()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLevel.adapter = adapter
    }

    private fun seeSpinnerForGenre() {
        val adapter: ArrayAdapter<Genre> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            Genre.values()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGenre.adapter = adapter
    }

    private fun isValidMovieDTO(): Boolean {
        binding.apply {
            return if (edFilmName.text.toString() == ""){
                Toast.makeText(context, "Укажите название фильма!", Toast.LENGTH_SHORT).show()
                false
            } else if (edCountry.text.toString() == ""){
                Toast.makeText(context, "Укажите страну!", Toast.LENGTH_SHORT).show()
                false
            } else {
                true;
            }
        }
    }

    private fun customizeScreen(b: Boolean, b1: Boolean) {
        binding.apply {
            if (b) seeFilm.visibility = View.VISIBLE
            else seeFilm.visibility = View.GONE
            if (b1) createFilm.visibility = View.VISIBLE
            else createFilm.visibility = View.GONE
        }
    }

    private fun showPopupMenu() {
        val popupMenu = PopupMenu(context, view)
        popupMenu.menu.add(0, Genre.ACTION.getCode(), Menu.NONE, "${context?.getString(R.string.action)}")
        popupMenu.menu.add(0, Genre.ADVENTURES.getCode(), Menu.NONE, "${context?.getString(R.string.adventures)}")
        popupMenu.menu.add(0, Genre.DRAMA.getCode(), Menu.NONE, "${context?.getString(R.string.drama)}")
        popupMenu.menu.add(0, Genre.COMEDY.getCode(), Menu.NONE, "${context?.getString(R.string.comedy)}")
        popupMenu.menu.add(0, Genre.FANTASTIC.getCode(), Menu.NONE, "${context?.getString(R.string.fantastic)}")
        popupMenu.setOnMenuItemClickListener{
            when (it.itemId){
                Genre.ACTION.getCode()-> {

                }
                Genre.ADVENTURES.getCode() -> {

                }
                Genre.DRAMA.getCode() -> {

                }
                Genre.COMEDY.getCode() -> {

                }
                Genre.FANTASTIC.getCode() -> {

                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    companion object {
        @JvmStatic
        fun newInstance(page: Int): HomeForViewingPage2Fragment {
            val fragment: HomeForViewingPage2Fragment = HomeForViewingPage2Fragment()
            val args = Bundle()
            args.putInt("num", page)
            fragment.arguments = args
            return fragment
        }
    }
}