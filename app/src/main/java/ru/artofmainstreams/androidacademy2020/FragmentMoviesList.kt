package ru.artofmainstreams.androidacademy2020

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.artofmainstreams.androidacademy2020.data.models.Movie

/**
 * Фрагмент со списком фильмов
 *
 * @author Andrei Khromov on 08.01.2021
 */
class FragmentMoviesList : Fragment() {
    private var clickListener: OnClickListener? = null
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        clickListener = context as? OnClickListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.rv_movies)
        adapter = MovieAdapter(movies, clickListener)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler.adapter = adapter
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(GridSpacingItemDecoration(12))
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    companion object {
        val movies = listOf(
            Movie(like = false, name = "Avengers: End Game", "13+", "Action, Adventure, Drama",
                reviews = 125, duration = 137, rating = 4f, image = R.drawable.img_avengers),
            Movie(like = true, name = "Tenet", "16+", "Action, Sci-Fi, Thriller",
                reviews = 98, duration = 97, rating = 5f, image = R.drawable.img_tenet),
            Movie(like = false, name = "Black Widow", "13+", "Action, Adventure, Sci-Fi",
                reviews = 38,duration =  102, rating = 4f, image = R.drawable.img_black_widow),
            Movie(like = false, name = "Wonder Women 1984", "13+", "Action, Adventure, Fantasy",
                reviews = 74, duration = 120, rating = 4f, image = R.drawable.img_wonder_woman_1984)
        )
    }
}

interface OnClickListener {
    fun onClick()
}
