package com.sangmee.mvvmarchitecture.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sangmee.mvvmarchitecture.R
import com.sangmee.mvvmarchitecture.model.Item
import com.sangmee.mvvmarchitecture.model.Movie
import com.sangmee.mvvmarchitecture.service.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movie_list.apply {
            adapter = movieAdapter
            setHasFixedSize(true)
        }

        btn_search.setOnClickListener {
            val query = et_query.text.toString()
            if (query.isEmpty()) {
                showMessage(getString(R.string.no_word))
            } else {
                rv_movie_list.layoutManager?.scrollToPosition(0)
                callMovieList(query)
            }
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun callMovieList(query: String) {
        RetrofitClient.getService().getMovieList(query).enqueue(object :
            Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        val items = ArrayList<Item>(it.items)
                        if (items.isEmpty()) {
                            movieAdapter.clearItems()
                            showMessage(getString(R.string.no_result))
                        } else {
                            movieAdapter.clearAndAddItems(items)
                        }
                    }
                } else {
                    showMessage(getString(R.string.net_error))
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                showMessage(t.message.toString())
            }
        })
    }
}
