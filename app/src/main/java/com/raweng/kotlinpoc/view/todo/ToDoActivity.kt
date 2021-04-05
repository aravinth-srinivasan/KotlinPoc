package com.raweng.kotlinpoc.view.todo
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.raweng.kotlinpoc.R
import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.viewModel.ToDoViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ToDoActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel:ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 1..5){
            viewModel.getToDoResponse(i).observe(this, Observer {
            when(it){
                is Resource.Success -> {
                    Log.e("TAG", "onCreate: Data Success")
                }
                is Resource.Loading -> {
                    Log.e("TAG", "onCreate: Data Loading")
                }
                is Resource.Error -> {
                    Log.e("TAG", "onCreate: Data Error")
                }
            }
        })
        }
    }
}