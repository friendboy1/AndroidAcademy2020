package ru.artofmainstreams.androidacademy2020.data.models

/**
 * Данные о фильме
 *
 * @property like поставлен ли лайк
 * @property name название фильма
 * @property pg возрастное ограничение
 * @property genre жанр фильма
 * @property reviews количество рецензий
 * @property duration продолжительность фильма
 * @property rating рейтинг фильма
 * @property image изображеие фильма
 *
 * @author Andrei Khromov on 24.04.2021
 */
data class Movie(
    val like: Boolean,
    val name: String,
    val pg: String,
    val genre: String,
    val reviews: Int,
    val duration: Int,
    val rating: Float,
    val image: Int
)