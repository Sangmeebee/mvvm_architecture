package com.sangmee.mvvmarchitecture.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sangmee.mvvmarchitecture.R
import com.sangmee.mvvmarchitecture.data.dataSource.local.LocalDataSourceImpl
import com.sangmee.mvvmarchitecture.data.dataSource.remote.RemoteDataSourceImpl
import com.sangmee.mvvmarchitecture.data.repository.MovieSearchRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieSearchRepository by lazy {
        MovieSearchRepositoryImpl(LocalDataSourceImpl(), RemoteDataSourceImpl())
    }
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
                movieSearchRepository.callMovieList(query, {
                    if (it.isEmpty()) {
                        movieAdapter.clearItems()
                        showMessage(getString(R.string.no_result))
                    } else {
                        movieAdapter.clearAndAddItems(it)
                    }
                }, { showMessage(it) })
            }
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
