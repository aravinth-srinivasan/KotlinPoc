package com.raweng.kotlinpoc.view.todo.domain.usecase

import com.raweng.kotlinpoc.view.todo.domain.repository.ToDoRepository
import javax.inject.Inject

class GetNormalRequestUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    suspend fun execute(id: Int)=toDoRepository.getToDoRes(id)

}