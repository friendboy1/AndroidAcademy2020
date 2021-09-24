package ru.artofmainstreams.androidacademy2020.data.models

/**
 * Данные о фильме
 *
 * @property id идентификатор
 * @property pgAge возрастное ограничение
 * @property title название фильма
 * @property genres жанр фильма
 * @property runningTime год выпуска
 * @property reviewCount количество рецензий
 * @property isLiked поставлен ли лайк
 * @property rating рейтинг фильма
 * @property imageUrl изображеие фильма
 * @property detailImageUrl
 * @property storyLine продолжительность фильма
 * @property actors список актёров
 *
 * @author Andrei Khromov on 24.04.2021
 */
data class Movie(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: List<Genre>,
    val runningTime: Int,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val imageUrl: String,
    val detailImageUrl: String,
    val storyLine: String,
    val actors: List<Actor>,
)