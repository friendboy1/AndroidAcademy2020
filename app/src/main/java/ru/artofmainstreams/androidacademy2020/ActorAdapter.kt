package ru.artofmainstreams.androidacademy2020

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.artofmainstreams.androidacademy2020.data.models.Actor

/**
 * Adapter элемента с данными об актёре
 *
 * @author Andrei Khromov on 24.04.2021
 */
class ActorAdapter(private val actors: List<Actor>) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder = ActorViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
    )

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.tv_actor_name)
        private val image: ImageView = itemView.findViewById(R.id.iv_actor)

        fun bind(actor: Actor) {
            name.text = actor.name
            image.setImageResource(actor.image)
        }
    }
}