package com.raweng.kotlinpoc.view.todo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.raweng.kotlinpoc.R
import com.raweng.kotlinpoc.databinding.ActivityToDoBinding
import com.raweng.kotlinpoc.utils.Coroutines
import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.viewModel.ToDoViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.StringBuilder
import javax.inject.Inject

class ToDoActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: ToDoViewModel
    lateinit var binding:ActivityToDoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_to_do)
        getSingleRequest()
        getParallelRequest5()
    }

    private  fun getParallelRequest5(){
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getParallelRequest5().collect{ it ->
                when (it) {
                    is Resource.Success -> {
                        val data=StringBuilder("")
                        withContext(Dispatchers.Main) {
                            it.data?.forEach { it ->
                                when (it) {
                                    is Resource.Success -> {
                                        hideLoading()
                                        it.data?.let {
                                            Log.e("TAG", "getParallelRequest5: ${it.title}")
                                            data.append(it.title)
                                            data.append("\n")
                                            binding.tvParallelData.text=data.toString()
                                        }
                                    }

                                    is Resource.Loading -> {
                                        showLoading()
                                    }
                                    is Resource.Error -> {
                                        Toast.makeText(this@ToDoActivity, it.message, Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }

                    }
                    is Resource.Loading -> {
                        withContext(Dispatchers.Main){
                            binding.vsLoading.viewStub?.inflate()
                        }

                    }
                    is Resource.Error -> {
                        Log.e("TAG", "onCreate: Data Flow Error 1")
                    }
                }
            }
        }
    }

    private fun getSingleRequest(){
        Coroutines.main {
            viewModel.getToDoReq(1).observe(this, Observer { it ->
                when(it){
                    is Resource.Success -> {
                        it.data?.let {
                            hideLoading()
                            binding.tvTitle.text=it.title
                            binding.tvContent.text=it.body
                        }
                    }
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Error -> {
                        hideLoading()
                        Log.e("TAG", "getSingleRequest: Error" )
                    }
                }
            })
        }
    }


    private fun getParallelRequest(){
        Coroutines.main {

        }
    }


    private fun showLoading(){
        binding.vsLoading.viewStub?.inflate()
    }

    private fun hideLoading(){
        binding.vsLoading.root.visibility=View.GONE
    }

}
