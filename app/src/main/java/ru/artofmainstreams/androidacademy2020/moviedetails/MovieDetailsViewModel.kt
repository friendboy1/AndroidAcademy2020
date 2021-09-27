package ru.artofmainstreams.androidacademy2020.moviedetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.artofmainstreams.androidacademy2020.data.MovieRepository
import ru.artofmainstreams.androidacademy2020.data.models.Movie

/**
 * ViewModel списка фильмов
 *
 * @author Andrei Khromov on 27.09.2021
 */
class MovieDetailsViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    suspend fun load(id: Int) {
        _movie.value = repository.loadMovie(id)
    }
}

/**
 * Фабрика для создания ViewModel'и
 */
class MovieDetailsViewModelFactory(
    private val repository: MovieRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct ${MovieDetailsViewModel::class.simpleName}")
    }
}