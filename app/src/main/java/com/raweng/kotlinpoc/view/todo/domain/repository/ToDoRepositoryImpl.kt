package com.raweng.kotlinpoc.view.todo.domain.repository

import android.util.Log
import com.raweng.kotlinpoc.api.WebServices
import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.utils.SafeApiRequest
import com.raweng.kotlinpoc.view.todo.data.model.ImageResponse
import com.raweng.kotlinpoc.view.todo.data.model.ToDoResponse
import javax.inject.Inject
import javax.inject.Named

class ToDoRepositoryImpl @Inject constructor(private  val webServices: WebServices): ToDoRepository,SafeApiRequest() {


    override fun getToDoResponse(id:Int): Resource<ToDoResponse> {
        var resource:Resource<ToDoResponse> = Resource.Loading()
       val request= webServices.getToDoList(id).execute()
        request.let {
            if (it.isSuccessful){
                it.body()?.let {body->
                   resource=Resource.Success(body)
                }
            }else{
                resource=Resource.Error("Some thing went wrong")
            }
        }
        return  resource
    }

    override suspend fun getToDoRes(id: Int): Resource<ToDoResponse> {
        Log.e("TAG", "getToDoRes: Call")
        return apiRequest { webServices.getToDoListRes(id) }
    }

    suspend fun getRes():Resource<ImageResponse> {
        return apiRequest { webServices.getUnSplash1() }
    }


}