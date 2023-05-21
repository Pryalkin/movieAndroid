package com.bsuir.moviesearchsystem.app.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.moviesearchsystem.Singletons
import com.bsuir.moviesearchsystem.app.dto.MovieAnswerDTO
import com.bsuir.moviesearchsystem.app.dto.utils.HttpResponse
import com.bsuir.moviesearchsystem.app.dto.MovieDTO
import com.bsuir.moviesearchsystem.app.repository.HomeRepository
import com.bsuir.moviesearchsystem.app.utils.MutableLiveEvent
import com.bsuir.moviesearchsystem.app.utils.MutableUnitLiveEvent
import com.bsuir.moviesearchsystem.app.utils.publishEvent
import com.bsuir.moviesearchsystem.app.utils.share
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel (
    private val homeRepository: HomeRepository = Singletons.homeRepository
): ViewModel() {

    private val _message = MutableLiveEvent<String>()
    val message = _message.share()

    private val _movies = MutableLiveData<List<MovieAnswerDTO>>()
    val movies = _movies.share()

    private val _movie = MutableLiveData<MovieAnswerDTO>()
    val movie = _movie.share()

    private val _navigateToTabsEvent = MutableUnitLiveEvent()
    val navigateToTabsEvent = _navigateToTabsEvent.share()

    fun getRole(): String {
        return homeRepository.getRole()!!
    }

    fun registration(movieDTO: MovieDTO) {
        viewModelScope.launch {
            var res: Response<List<MovieAnswerDTO>> = homeRepository.registration(movieDTO)
            if (res.isSuccessful){
                _movies.value = res.body()
                showToast("Вы успешно зарегистрировали фильм!")
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }

    fun getAllFilms() {
        viewModelScope.launch {
            var res: Response<List<MovieAnswerDTO>> = homeRepository.getAllFilms()
            if (res.isSuccessful){
                _movies.value = res.body()
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }

    private fun showToast(mes: String) = _message.publishEvent(mes)

    fun getMovie(id: Long) {
        viewModelScope.launch {
            var res: Response<MovieAnswerDTO> = homeRepository.getMovie(id)
            if (res.isSuccessful){
                _movie.value = res.body()
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }

    fun getMoviesFind(find: String) {
        viewModelScope.launch {
            var res: Response<List<MovieAnswerDTO>> = homeRepository.getMoviesFind(find)
            if (res.isSuccessful){
                _movies.value = res.body()
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }


}