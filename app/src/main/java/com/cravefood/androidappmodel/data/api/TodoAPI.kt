package com.cravefood.androidappmodel.data.api

import com.cravefood.androidappmodel.data.model.TodoResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoAPI {

    @GET("todos/")
    fun getTodos(): Call<List<TodoResponseModel>>

    @GET("todos/{id}")
    fun getTodo(
        @Path("id") id: Int
    ): Call<TodoResponseModel>

}