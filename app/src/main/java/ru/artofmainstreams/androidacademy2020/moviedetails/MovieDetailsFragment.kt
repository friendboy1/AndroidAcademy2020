package ru.artofmainstreams.androidacademy2020.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import ru.artofmainstreams.androidacademy2020.R
import ru.artofmainstreams.androidacademy2020.data.models.Movie
import ru.artofmainstreams.androidacademy2020.di.MovieRepositoryProvider
import ru.artofmainstreams.androidacademy2020.movies.MovieListViewModel
import ru.artofmainstreams.androidacademy2020.movies.MovieListViewModelFactory

/**
 * Фрагмент с отображением детальной информацией о фильме
 *
 * @author Andrei Khromov on 08.01.2021
 */
class MovieDetailsFragment : Fragment() {

    private var listener: MovieDetailsBackClickListener? = null

    private lateinit var viewModel: MovieDetailsViewModel

    private lateinit var recycler: RecyclerView
    private lateinit var actorAdapter: ActorAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieDetailsBackClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getSerializable(PARAM_MOVIE_ID) as? Int ?: return
        actorAdapter = ActorAdapter()
        recycler = view.findViewById<RecyclerView>(R.id.rv_actors).apply {
            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this.adapter = actorAdapter
        }

        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            listener?.onMovieDeselected()
        }

        viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModelFactory(
                (requireActivity() as MovieRepositoryProvider)
                    .provideMovieRepository()
            )
        ).get(MovieDetailsViewModel::class.java)
        lifecycleScope.launch {
            viewModel.load(movieId)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.movie.observe(this, {
            if (it != null) {
                bindUI(it)
            } else {
                showMovieNotFoundError()
            }
        })
    }

    private fun showMovieNotFoundError() {
        Toast.makeText(requireContext(), R.string.error_movie_not_found, Toast.LENGTH_LONG).show()
    }

    private fun bindUI(movie: Movie) {
        updateMovieDetailsInfo(movie)
        actorAdapter.submitList(movie.actors)
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    private fun updateMovieDetailsInfo(movie: Movie) {
        val logo = view?.findViewById<ImageView>(R.id.movie_logo_image)
        logo?.let { Glide.with(it).load(movie.detailImageUrl).into(logo) }

        view?.findViewById<TextView>(R.id.movie_age_restrictions_text)?.text =
            context?.getString(R.string.movies_list_allowed_age_template, movie.pgAge)

        view?.findViewById<TextView>(R.id.film_name_text)?.text = movie.title
        view?.findViewById<TextView>(R.id.film_genre_text)?.text =
            movie.genres.joinToString { it.name }
        view?.findViewById<TextView>(R.id.movie_reviews_count_text)?.text =
            context?.getString(R.string.movies_list_reviews_template, movie.reviewCount)
        view?.findViewById<TextView>(R.id.movie_storyline_text)?.text = movie.storyLine
        view?.findViewById<RatingBar>(R.id.rating_bar)?.rating = (movie.rating / 2).toFloat()
    }

    interface MovieDetailsBackClickListener {
        fun onMovieDeselected()
    }

    companion object {
        private const val PARAM_MOVIE_ID = "movie_id"

        fun create(movieId: Int) = MovieDetailsFragment().also {
            val args = bundleOf(
                PARAM_MOVIE_ID to movieId
            )
            it.arguments = args
        }
    }
}