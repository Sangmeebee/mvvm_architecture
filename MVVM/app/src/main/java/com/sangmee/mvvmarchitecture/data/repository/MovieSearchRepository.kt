package com.sangmee.mvvmarchitecture.data.repository

import com.sangmee.mvvmarchitecture.data.model.Item

interface MovieSearchRepository {
    fun callMovieList(query: String, success: (ArrayList<Item>) -> Unit, failure: (String) -> Unit)
}
