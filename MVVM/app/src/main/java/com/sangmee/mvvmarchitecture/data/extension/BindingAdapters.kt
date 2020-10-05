package com.sangmee.mvvmarchitecture.data.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sangmee.mvvmarchitecture.data.model.Item
import com.sangmee.mvvmarchitecture.ui.MovieAdapter

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

@BindingAdapter("items")
fun RecyclerView.setItems(items: ArrayList<Item>?){
    val movieAdapter = MovieAdapter()
    this.adapter = movieAdapter
    this.setHasFixedSize(true)
    items?.let {
        movieAdapter.clearAndAddItems(it)
    }
}
