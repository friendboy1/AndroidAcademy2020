package ru.artofmainstreams.androidacademy2020.movies

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
class MovieListViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    suspend fun load() {
        _movies.value = repository.loadMovies()
    }
}

/**
 * Фабрика для создания ViewModel'и
 */
class MovieListViewModelFactory(
    private val repository: MovieRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct ${MovieListViewModel::class.simpleName}")
    }
}