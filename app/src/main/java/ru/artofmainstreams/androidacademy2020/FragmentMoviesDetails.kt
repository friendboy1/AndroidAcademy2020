package ru.artofmainstreams.androidacademy2020

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.artofmainstreams.androidacademy2020.data.models.Actor

/**
 * Фрагмент с отображением детальной информацией о фильме
 *
 * @author Andrei Khromov on 08.01.2021
 */
class FragmentMoviesDetails : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ActorAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            activity?.onBackPressed()
        }
        recycler = view.findViewById(R.id.rv_actors)
        adapter = ActorAdapter(actors)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
    }

    companion object {
        val actors = listOf(
            Actor("Robert Downey Jr.", R.drawable.img_actor1),
            Actor("Chris Evans", R.drawable.img_actor2),
            Actor("Mark Ruffalo", R.drawable.img_actor3),
            Actor("Chris Hemsworth", R.drawable.img_actor4)
        )
    }
}