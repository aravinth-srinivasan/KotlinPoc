package com.raweng.kotlinpoc.di.module

import com.raweng.kotlinpoc.di.annotation.ActivityScope
import com.raweng.kotlinpoc.view.todo.ToDoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeToDoActivity(): ToDoActivity



}