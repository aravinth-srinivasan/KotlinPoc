package com.raweng.kotlinpoc.view.todo.domain.usecase

import com.raweng.kotlinpoc.view.todo.domain.repository.ToDoRepository
import javax.inject.Inject

class GetParallelRequestUseCase @Inject constructor(private val toDoRepository: ToDoRepository){

    fun execute(id: Int)=toDoRepository.getToDoResponse(id)

}