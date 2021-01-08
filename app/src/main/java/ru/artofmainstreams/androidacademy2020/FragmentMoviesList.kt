package ru.artofmainstreams.androidacademy2020

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

/**
 * Фрагмент со списком фильмов
 *
 * @author Andrei Khromov on 08.01.2021
 */
class FragmentMoviesList : Fragment() {
    private var clickListener: OnClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListener) {
            clickListener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view.findViewById<MaterialCardView>(R.id.cv_movie1).setOnClickListener {
            clickListener?.onClick()
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }
}

interface OnClickListener {
    fun onClick()
}
