package com.raweng.kotlinpoc.view.todo.model

data class ToDoResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)