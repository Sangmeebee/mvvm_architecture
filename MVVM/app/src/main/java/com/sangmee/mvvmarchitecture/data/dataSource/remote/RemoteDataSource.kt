package com.sangmee.mvvmarchitecture.data.dataSource.remote

import com.sangmee.mvvmarchitecture.data.model.Item

interface RemoteDataSource {
    fun callMovieList(query: String, success: (ArrayList<Item>) -> Unit, failure: (String) -> Unit)
}
