package com.raweng.kotlinpoc.view.todo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.model.ToDoResponse
import com.raweng.kotlinpoc.view.todo.repository.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToDoViewModel @Inject constructor(val toDoRepository: ToDoRepository) : ViewModel() {


    fun getToDoResponse(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(toDoRepository.getToDoResponse(id))
    }


    fun getToDoResponseFlow(id: Int) = flow {
        emit(toDoRepository.getToDoResponse(id))
    }.onStart {
        emit(Resource.Loading())
    }.flowOn(Dispatchers.IO)
        .onCompletion {
            Log.e("TAG", "getToDoResponseFlow: Comp")
        }


    fun getParallelRequest4()=flow{
            flowOf(1,2,3,4)
                .map { value -> getToDoResponseFlow(value)}
                .collect { response ->
                    response.collect {
                        emit(it)
                    }
                }
        }


}