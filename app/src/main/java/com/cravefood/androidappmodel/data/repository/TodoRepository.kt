package com.cravefood.androidappmodel.data.repository

import com.cravefood.androidappmodel.data.api.TodoAPI
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.data.repository.repository_util.RetrofitBuilder
import com.cravefood.androidappmodel.util.RequestCallback

interface TodoRepository {

    fun getTodos(
        callback: RequestCallback<List<TodoResponseModel>>
    )

    fun getTodo(
        id: Int,
        callback: RequestCallback<TodoResponseModel>
    )

}

class TodoRepositoryImpl(private val retrofitBuilder: RetrofitBuilder) : TodoRepository {
    override fun getTodos(callback: RequestCallback<List<TodoResponseModel>>) {
        retrofitBuilder
            .build(TodoAPI::class.java)
            .getTodos()
            .enqueue(callback)
    }

    override fun getTodo(id: Int, callback: RequestCallback<TodoResponseModel>) {
        retrofitBuilder
            .build(TodoAPI::class.java)
            .getTodo(id)
            .enqueue(callback)
    }
}