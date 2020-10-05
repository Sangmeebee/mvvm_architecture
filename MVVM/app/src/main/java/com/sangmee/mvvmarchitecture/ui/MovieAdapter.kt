package com.sangmee.mvvmarchitecture.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.mvvmarchitecture.R
import com.sangmee.mvvmarchitecture.data.model.Item
import com.sangmee.mvvmarchitecture.databinding.MovieItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MoiveViewHolder>() {
    private val movieList = arrayListOf<Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoiveViewHolder {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        return MoiveViewHolder(binding)
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

    class MoiveViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.item = item
        }
    }
}
