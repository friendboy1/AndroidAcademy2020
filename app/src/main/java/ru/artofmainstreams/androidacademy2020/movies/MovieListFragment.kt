package ru.artofmainstreams.androidacademy2020.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.artofmainstreams.androidacademy2020.GridSpacingItemDecoration
import ru.artofmainstreams.androidacademy2020.R
import ru.artofmainstreams.androidacademy2020.data.models.Movie
import ru.artofmainstreams.androidacademy2020.di.MovieRepositoryProvider

/**
 * Фрагмент со списком фильмов
 *
 * @author Andrei Khromov on 08.01.2021
 */
class MovieListFragment : Fragment() {
    private var listener: MoviesListItemClickListener? = null

    private lateinit var viewModel: MovieListViewModel

    private lateinit var recycler: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MoviesListItemClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieAdapter = MovieAdapter { movieData ->
            listener?.onMovieSelected(movieData)
        }
        recycler = view.findViewById<RecyclerView>(R.id.rv_movies).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)
            this.adapter = movieAdapter
        }
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(GridSpacingItemDecoration(12))

        viewModel = ViewModelProvider(
            this,
            MovieListViewModelFactory(
                (requireActivity() as MovieRepositoryProvider)
                    .provideMovieRepository()
            )
        ).get(MovieListViewModel::class.java)
        lifecycleScope.launch {
            viewModel.load()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.movies.observe(this, {
            movieAdapter.submitList(it)
        })
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    companion object {
        fun create() = MovieListFragment()
    }

    interface MoviesListItemClickListener {
        fun onMovieSelected(movie: Movie)
    }
}

