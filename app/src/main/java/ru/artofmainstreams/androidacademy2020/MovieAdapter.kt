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
import androidx.recyclerview.widget.RecyclerView
import ru.artofmainstreams.androidacademy2020.data.models.Movie

/**
 * Adapter элемента с данными о фильме
 *
 * @author Andrei Khromov on 24.04.2021
 */
class MovieAdapter(private val movies: List<Movie>, private val clickListener: OnClickListener?) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            clickListener?.onClick()
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val like: ImageView = itemView.findViewById(R.id.iv_like)
        private val name: TextView = itemView.findViewById(R.id.tv_title)
        private val pg: TextView = itemView.findViewById(R.id.tv_pg)
        private val genre: TextView = itemView.findViewById(R.id.tv_genre)
        private val reviews: TextView = itemView.findViewById(R.id.tv_reviews)
        private val duration: TextView = itemView.findViewById(R.id.tv_duration)
        private val rating: RatingBar = itemView.findViewById(R.id.rating_bar)
        private val image: ImageView = itemView.findViewById(R.id.iv_movie)

        fun bind(movie: Movie) {
            val tint = if (movie.like) R.color.pink else R.color.grayLight
            ImageViewCompat.setImageTintList(like,
                ColorStateList.valueOf(ContextCompat.getColor(itemView.context, tint)));
            name.text = movie.name
            pg.text = movie.pg
            genre.text = movie.genre
            reviews.text = String.format(itemView.resources.getString(R.string.movie_reviews), movie.reviews)
            duration.text = String.format(itemView.resources.getString(R.string.movie_duration), movie.duration)
            rating.rating = movie.rating
            image.setImageResource(movie.image)
        }
    }
}