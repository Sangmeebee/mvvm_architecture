package com.sangmee.mvvmarchitecture.ui

import androidx.databinding.ObservableField
import com.sangmee.mvvmarchitecture.data.model.Item
import com.sangmee.mvvmarchitecture.data.repository.MovieSearchRepository

class MainViewModel(private val repository: MovieSearchRepository) {
    val query = ObservableField<String>()
    val movieList = ObservableField<ArrayList<Item>>()

    fun searchMovie() {
        val query = query.get() ?: return
        repository.callMovieList(query, {
            movieList.set(it)
        }, {
            showToastEvent.notifyChange()
        })
    }

    val showToastEvent = ObservableField<Unit>()
}
