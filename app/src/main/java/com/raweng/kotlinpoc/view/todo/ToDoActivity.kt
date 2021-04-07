package com.raweng.kotlinpoc.view.todo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.raweng.kotlinpoc.R
import com.raweng.kotlinpoc.databinding.ActivityToDoBinding
import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.model.ToDoResponse
import com.raweng.kotlinpoc.view.todo.viewModel.ToDoViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ToDoActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: ToDoViewModel
    lateinit var binding: ActivityToDoBinding
    private val data = StringBuilder("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_to_do)
        initRequests()
    }


    private fun initRequests() {
        getSingleRequest()
        getParallelRequest()
    }


    private fun getSingleRequest() {
        viewModel.getToDoReq(1).observe(this, Observer {
            onSingleRequestHandler(it)
        })

    }


    private fun getParallelRequest() {
        viewModel.getParallelRequest5().asLiveData(Dispatchers.IO).observe(this, Observer {
            onParallelRequestObserver(it)
        })
    }


    private fun onParallelRequestObserver(response: Resource<out MutableList<Resource<ToDoResponse>>>) {
        when (response) {
            is Resource.Success -> {
                hideLoading()
                response.data?.forEach {
                    onEachItemResponseHandler(it)
                }
            }
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Error -> {

            }
        }
    }


    private fun onEachItemResponseHandler(response: Resource<ToDoResponse>) {
        when (response) {
            is Resource.Success -> {
                response.data?.let {
                    onEachItemSuccessResponseHandle(it)
                }
            }

            is Resource.Loading -> {

            }
            is Resource.Error -> {
                Toast.makeText(this@ToDoActivity, response.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun onEachItemSuccessResponseHandle(response:ToDoResponse){
        data.append(response.title)
        data.append("\n")
        binding.tvParallelData.text = data.toString()
    }

    private fun onSingleRequestHandler(resource: Resource<ToDoResponse>) {
        when (resource) {
            is Resource.Success -> {
                hideLoading()
                resource.data?.let {
                    onSingleRequestSuccessHandler(it)
                }
            }
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Error -> {
                hideLoading()
            }
        }
    }


    private fun onSingleRequestSuccessHandler(data:ToDoResponse){
            binding.tvTitle.text = data.title
            binding.tvContent.text = data.body

    }


    private fun showLoading() {
        binding.vsLoading.viewStub?.inflate()
    }

    private fun hideLoading() {
        binding.vsLoading.root?.apply {
            visibility = View.GONE
        }
    }

}
