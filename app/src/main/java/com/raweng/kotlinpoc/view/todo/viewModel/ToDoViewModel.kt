package com.raweng.kotlinpoc.view.todo.viewModel

import androidx.lifecycle.*
import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.model.ToDoResponse
import com.raweng.kotlinpoc.view.todo.repository.ToDoRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ToDoViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {



    fun getParallelRequest5() = flow {
        val f1 = flowOf(toDoRepository.getToDoResponse(1))
        val f2 = flowOf(toDoRepository.getToDoResponse(2))
        f1.zip(f2) { flow1, flow2 ->
            val flowList = mutableListOf<Resource<ToDoResponse>>()
            flowList.add(flow1)
            flowList.add(flow2)
            return@zip flowList
        }.flowOn(Dispatchers.IO)
                .catch { e ->
                    emit(Resource.Error(e.message.toString(), null))
                }
                .onStart {
                    emit(Resource.Loading())
                }.collect {
                    emit(Resource.Success(it))
                }
    }



    fun getToDoReq(id:Int):LiveData<Resource<ToDoResponse>>{
        val toDoRes=MutableLiveData<Resource<ToDoResponse>>()
        toDoRes.postValue(Resource.Loading())
        CoroutineScope(Dispatchers.IO).launch {
            toDoRes.postValue(toDoRepository.getToDoRes(id))
        }

        return toDoRes
    }




}