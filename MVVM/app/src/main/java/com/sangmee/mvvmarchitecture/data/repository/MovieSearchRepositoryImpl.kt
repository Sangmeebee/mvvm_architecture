package com.sangmee.mvvmarchitecture.data.repository

import com.sangmee.mvvmarchitecture.data.dataSource.local.LocalDataSource
import com.sangmee.mvvmarchitecture.data.dataSource.remote.RemoteDataSource
import com.sangmee.mvvmarchitecture.data.model.Item

class MovieSearchRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieSearchRepository {
    override fun callMovieList(
        query: String,
        success: (ArrayList<Item>) -> Unit,
        failure: (String) -> Unit
    ) {
        remoteDataSource.callMovieList(query, success, failure)
    }
}
