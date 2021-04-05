package com.raweng.kotlinpoc.di.component

import android.app.Application
import com.raweng.kotlinpoc.app.App
import com.raweng.kotlinpoc.di.module.ActivityModule
import com.raweng.kotlinpoc.di.module.NetModule
import com.raweng.kotlinpoc.view.todo.di.ToDoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetModule::class,
    ActivityModule::class,
    ToDoModule::class
])

interface AppComponent :AndroidInjector<App>{

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindApplication(application: Application):Builder
        fun build():AppComponent
    }

}