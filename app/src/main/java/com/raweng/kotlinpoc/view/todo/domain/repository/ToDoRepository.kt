package com.raweng.kotlinpoc.view.todo.domain.repository

import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.data.model.ToDoResponse

interface ToDoRepository {

    fun getToDoResponse(id:Int): Resource<ToDoResponse>
    suspend fun getToDoRes(id:Int): Resource<ToDoResponse>

}