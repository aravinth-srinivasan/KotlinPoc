package com.raweng.kotlinpoc.api

import com.raweng.kotlinpoc.view.todo.model.ToDoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface WebServices {

    @GET("posts/{id}")
    fun getToDoList(@Path("id") id:Int): Call<ToDoResponse>

}