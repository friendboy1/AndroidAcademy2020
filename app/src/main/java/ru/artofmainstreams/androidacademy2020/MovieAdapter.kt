package ru.artofmainstreams.androidacademy2020

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.artofmainstreams.androidacademy2020.data.models.Movie

/**
 * Adapter элемента с данными о фильме
 *
 * @author Andrei Khromov on 24.04.2021
 */
class MovieAdapter(private val onClickCard: (item: Movie) -> Unit) :
    ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickCard)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        private val likeImage: ImageView = itemView.findViewById(R.id.movie_like_image)
        private val pgText: TextView = itemView.findViewById(R.id.pg_text)
        private val genreText: TextView = itemView.findViewById(R.id.film_genre_text)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.rating_bar)
        private val reviewsText: TextView = itemView.findViewById(R.id.movie_reviews_count_text)
        private val titleText: TextView = itemView.findViewById(R.id.film_name_text)
        private val movieLenText: TextView = itemView.findViewById(R.id.film_time_text)

        fun bind(item: Movie, onClickCard: (item: Movie) -> Unit) {
            Glide.with(itemView).load(item.imageUrl).into(movieImage)

            val context = itemView.context
            pgText.text =
                context.getString(R.string.movies_list_allowed_age_template, item.pgAge)
            genreText.text = item.genres.joinToString { it.name }
            reviewsText.text =
                context.getString(R.string.movies_list_reviews_template, item.reviewCount)
            titleText.text = item.title
            movieLenText.text = context.getString(R.string.movies_list_film_time, item.runningTime)

            val likeColor = if (item.isLiked) {
                R.color.pinkLight
            } else {
                R.color.white
            }
            ImageViewCompat.setImageTintList(
                likeImage, ColorStateList.valueOf(
                    ContextCompat.getColor(likeImage.context, likeColor)
                )
            )

            ratingBar.rating = (item.rating / 2).toFloat()

            itemView.setOnClickListener {
                onClickCard(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}