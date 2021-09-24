package ru.artofmainstreams.androidacademy2020.data.models

import java.io.Serializable

/**
 * Данные об актёре
 *
 * @property id идентификатор
 * @property name имя фамилия актёра
 * @property imageUrl фотография актёра
 *
 * @author Andrei Khromov on 24.04.2021
 */
data class Actor(
    val id: Int,
    val name: String,
    val imageUrl: String,
) : Serializable