package ru.artofmainstreams.androidacademy2020.di

import ru.artofmainstreams.androidacademy2020.data.MovieRepository

internal interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}