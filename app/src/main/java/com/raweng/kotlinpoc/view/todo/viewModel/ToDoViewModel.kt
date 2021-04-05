package com.raweng.kotlinpoc.view.todo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.model.ToDoResponse
import com.raweng.kotlinpoc.view.todo.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToDoViewModel @Inject constructor(private val toDoRepository: ToDoRepository):ViewModel() {


    fun getToDoResponse(id:Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(toDoRepository.getToDoResponse(id))
    }


    fun getToDoResponseFlow(id:Int)= flow {
        emit(Resource.Loading<ToDoResponse>())
        emit(Resource.Success(toDoRepository.getToDoResponse(id)))
    }



}