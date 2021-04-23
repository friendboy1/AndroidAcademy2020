package ru.artofmainstreams.androidacademy2020

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Фрагмент с отображением детальной информацией о фильме
 *
 * @author Andrei Khromov on 08.01.2021
 */
class FragmentMoviesDetails : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            activity?.onBackPressed()
        }
        return view
    }
}