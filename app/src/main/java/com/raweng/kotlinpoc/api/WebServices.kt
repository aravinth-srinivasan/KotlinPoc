package com.raweng.kotlinpoc.api

import com.raweng.kotlinpoc.view.todo.model.ImageResponse
import com.raweng.kotlinpoc.view.todo.model.ToDoResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface WebServices {

    @GET("posts/{id}")
    fun getToDoList(@Path("id") id:Int): Call<ToDoResponse>


    @GET("posts/{id}")
    suspend fun getToDoListRes(@Path("id") id:Int): Response<ToDoResponse>

    @Headers("Accept-Version: v1")
    @GET("photos")
    fun getUnSplash(@Query("client_id") client_id:String="sNx1UrXmX8qMQXNSy-zYxNx9_aYGZUI_RFU8YSAVbkI"):Call<ImageResponse>


    @Headers("Accept-Version: v1")
    @GET("photos")
    fun getUnSplash1(@Query("client_id") client_id:String="sNx1UrXmX8qMQXNSy-zYxNx9_aYGZUI_RFU8YSAVbkI"):Response<ImageResponse>

}