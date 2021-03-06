package com.raweng.kotlinpoc.view.todo.di

import com.raweng.kotlinpoc.api.WebServices
import com.raweng.kotlinpoc.view.todo.repository.ToDoRepository
import com.raweng.kotlinpoc.view.todo.repository.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ToDoModule {

    @Singleton
    @Provides
    fun provideToDoRepositoryImpl(webServices: WebServices):ToDoRepository{
        return ToDoRepositoryImpl(webServices)
    }
}