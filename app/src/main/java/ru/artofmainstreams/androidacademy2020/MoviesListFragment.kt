package ru.artofmainstreams.androidacademy2020

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.artofmainstreams.androidacademy2020.data.models.Movie
import ru.artofmainstreams.androidacademy2020.di.MovieRepositoryProvider

/**
 * Фрагмент со списком фильмов
 *
 * @author Andrei Khromov on 08.01.2021
 */
class MoviesListFragment : Fragment() {
    private var listener: MoviesListItemClickListener? = null

    private lateinit var recycler: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MoviesListItemClickListener) {
            listener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieAdapter = MovieAdapter { movieData ->
            listener?.onMovieSelected(movieData)
        }
        recycler = view.findViewById<RecyclerView>(R.id.rv_movies).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)
            this.adapter = movieAdapter
            loadDataToAdapter(movieAdapter)
        }
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(GridSpacingItemDecoration(12))
    }

    private fun loadDataToAdapter(adapter: MovieAdapter) {
        val repository = (requireActivity() as MovieRepositoryProvider).provideMovieRepository()
        lifecycleScope.launch {
            val moviesData = repository.loadMovies()

            adapter.submitList(moviesData)
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    companion object {
        fun create() = MoviesListFragment()
    }

    interface MoviesListItemClickListener {
        fun onMovieSelected(movie: Movie)
    }
}

