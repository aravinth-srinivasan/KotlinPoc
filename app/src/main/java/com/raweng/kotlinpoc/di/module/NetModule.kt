package com.raweng.kotlinpoc.di.module

import com.raweng.kotlinpoc.BuildConfig
import com.raweng.kotlinpoc.api.WebServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            readTimeout(30, TimeUnit.SECONDS)
            connectTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            followRedirects(false)
            followSslRedirects(false)

        }
        val client = okHttpClient.build()

        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideWebServices():WebServices{
        return provideRetrofit().create(WebServices::class.java)
    }

}