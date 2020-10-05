package com.sangmee.mvvmarchitecture.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.sangmee.mvvmarchitecture.R
import com.sangmee.mvvmarchitecture.data.dataSource.local.LocalDataSourceImpl
import com.sangmee.mvvmarchitecture.data.dataSource.remote.RemoteDataSourceImpl
import com.sangmee.mvvmarchitecture.data.repository.MovieSearchRepositoryImpl
import com.sangmee.mvvmarchitecture.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm by lazy{
        MainViewModel(MovieSearchRepositoryImpl(LocalDataSourceImpl(), RemoteDataSourceImpl()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.vm = vm

        vm.showToastEvent.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showMessage("에러발생")
            }
        })
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
