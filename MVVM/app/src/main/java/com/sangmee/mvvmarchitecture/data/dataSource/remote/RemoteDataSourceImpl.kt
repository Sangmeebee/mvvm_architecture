package com.sangmee.mvvmarchitecture.data.dataSource.remote

import com.sangmee.mvvmarchitecture.data.model.Item
import com.sangmee.mvvmarchitecture.data.model.Movie
import com.sangmee.mvvmarchitecture.data.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {
    override fun callMovieList(
        query: String,
        success: (ArrayList<Item>) -> Unit,
        failure: (String) -> Unit
    ) {
        RetrofitClient.getService().getMovieList(query).enqueue(object :
            Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        success(it.items)
                    }
                } else {
                    failure(response.message())
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                failure(t.message.toString())
            }
        })
    }
}
