package com.sangmee.mvvmarchitecture.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sangmee.mvvmarchitecture.R
import com.sangmee.mvvmarchitecture.data.model.Item

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MoiveViewHolder>() {
    private val movieList = arrayListOf<Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoiveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoiveViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: MoiveViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

    fun clearAndAddItems(items: ArrayList<Item>) {
        movieList.clear()
        movieList.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems(){
        movieList.clear()
    }

    class MoiveViewHolder(view: View, private val context: Context) :
        RecyclerView.ViewHolder(view) {
        private val ivPoster = view.findViewById<ImageView>(R.id.iv_poster)
        private val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        private val rbRatingBar = view.findViewById<RatingBar>(R.id.rb_grade_rating)
        private val tvDate = view.findViewById<TextView>(R.id.tv_date)
        private val tvDirector = view.findViewById<TextView>(R.id.tv_director)
        private val tvActor = view.findViewById<TextView>(R.id.tv_actor)

        fun bind(item: Item) {
            tvTitle.text = item.title
            rbRatingBar.rating = item.userRating.toFloat() / 2
            tvDate.text = item.pubDate.toString()
            tvDirector.text = item.director
            tvActor.text = item.actor

            Glide.with(context)
                .load(item.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPoster)
        }

    }
}
