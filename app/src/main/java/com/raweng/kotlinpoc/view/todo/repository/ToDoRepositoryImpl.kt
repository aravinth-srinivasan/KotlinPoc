package com.raweng.kotlinpoc.view.todo.repository

import com.raweng.kotlinpoc.api.WebServices
import com.raweng.kotlinpoc.utils.Resource
import com.raweng.kotlinpoc.view.todo.model.ToDoResponse
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val webServices: WebServices):ToDoRepository {


    override fun getToDoResponse(id:Int): Resource<ToDoResponse> {
        var resource:Resource<ToDoResponse> = Resource.Loading()
       val request= webServices.getToDoList(id).execute()
        request.let {
            if (it.isSuccessful){
                it.body()?.let {body->
                   // resource=Resource.Success(body)
                    resource=Resource.Error("Some thing went wrong")
                }
            }else{
                resource=Resource.Error("Some thing went wrong")
            }
        }
        return  resource
    }

}