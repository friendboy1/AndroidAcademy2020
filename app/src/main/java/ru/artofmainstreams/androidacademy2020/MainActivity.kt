package ru.artofmainstreams.androidacademy2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.artofmainstreams.androidacademy2020.data.JsonMovieRepository
import ru.artofmainstreams.androidacademy2020.data.MovieRepository
import ru.artofmainstreams.androidacademy2020.data.models.Movie
import ru.artofmainstreams.androidacademy2020.di.MovieRepositoryProvider
import ru.artofmainstreams.androidacademy2020.moviedetails.MovieDetailsFragment
import ru.artofmainstreams.androidacademy2020.movies.MovieListFragment

/**
 * Главный экран приложения
 *
 * @author Andrei Khromov on 08.01.2021
 */
class MainActivity : AppCompatActivity(),
    MovieListFragment.MoviesListItemClickListener,
    MovieDetailsFragment.MovieDetailsBackClickListener,
    MovieRepositoryProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        routeToMovieDetails(movie)
    }

    override fun onMovieDeselected() {
        routeBack()
    }

    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MovieListFragment.create(),
                MovieListFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MovieListFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeToMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                MovieDetailsFragment.create(movie.id),
                MovieDetailsFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MovieDetailsFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository = jsonMovieRepository
}