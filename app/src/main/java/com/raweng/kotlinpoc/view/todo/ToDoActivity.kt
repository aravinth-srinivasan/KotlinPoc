package com.raweng.kotlinpoc.view.todo

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.raweng.kotlinpoc.R
import com.raweng.kotlinpoc.databinding.ActivityToDoBinding
import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.viewModel.ToDoViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ToDoActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: ToDoViewModel
    lateinit var binding:ActivityToDoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_to_do)
        getParallelRequest1()
    }


   private fun getParallelRequest1(){
        for (i in 1..5) {
            viewModel.getToDoResponse(i).observe(this, Observer {
                when (it) {
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

    private fun getParallelRequest2(){
        for (i in 1..5) {

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getToDoResponseFlow(i).collect {
                    when (it) {
                        is Resource.Success -> {
                            Log.e("TAG", "onCreate: Data Flow Success")
                        }
                        is Resource.Loading -> {
                            Log.e("TAG", "onCreate: Data Flow Loading")
                        }
                        is Resource.Error -> {
                            Log.e("TAG", "onCreate: Data Flow Error")
                        }
                    }
                }
            }
        }
    }

   private fun getParallelRequest3(){

        runBlocking {
            (1..5).asFlow().map { it ->
                viewModel.getToDoResponseFlow(it)
                    .collect {
                        when (it) {
                            is Resource.Success -> {
                                Log.e("TAG", "onCreate: Data Flow Success 1")
                            }
                            is Resource.Loading -> {
                                Log.e("TAG", "onCreate: Data Flow Loading 1")
                            }
                            is Resource.Error -> {
                                Log.e("TAG", "onCreate: Data Flow Error 1")
                            }
                        }
                    }
            }
        }
    }

}
