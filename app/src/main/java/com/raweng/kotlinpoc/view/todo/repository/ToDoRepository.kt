package com.raweng.kotlinpoc.view.todo.repository

import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.model.ToDoResponse

interface ToDoRepository {

    fun getToDoResponse(id:Int): Resource<ToDoResponse>

}