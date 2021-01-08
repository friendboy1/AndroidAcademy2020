package ru.artofmainstreams.androidacademy2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Экран детальной информации о фильме
 *
 * @author Andrei Khromov on 16.11.2020
 */
class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_movies_details)
    }
}