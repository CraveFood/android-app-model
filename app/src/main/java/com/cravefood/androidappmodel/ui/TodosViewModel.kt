package com.cravefood.androidappmodel.ui

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.data.repository.TodoRepository
import com.cravefood.androidappmodel.util.RequestCallback

class TodosViewModel(
    private val todosRepository: TodoRepository
) : ViewModel() {

    private val _todos = MutableLiveData<List<TodoResponseModel>>().apply { value = emptyList() }
    val todos: LiveData<List<TodoResponseModel>>
        get() = _todos
    val progressBarVisibility = MutableLiveData<Int>().apply { value = View.GONE }

    fun getTodos(forceUpdate: Boolean = false) {
        if (!todos.value!!.isEmpty() && !forceUpdate) {
            //handleTodosResponse(todos)
            return
        }

        progressBarVisibility.value = View.VISIBLE

        todosRepository.getTodos(object : RequestCallback<List<TodoResponseModel>>() {

            override fun onRequestResponse(responseObject: List<TodoResponseModel>) {
                progressBarVisibility.value = View.GONE
                handleTodosResponse(responseObject)
            }

            override fun onRequestError(throwable: Throwable?) {
                progressBarVisibility.value = View.GONE
            }

            override fun onNoInternetConnection(message: String) {
                progressBarVisibility.value = View.GONE
            }

        })
    }

    fun onClickTodo(todo: TodoResponseModel, itemView: View) {
        Toast.makeText(itemView.context, "Item: " + todo.title, Toast.LENGTH_SHORT).show()
    }

    private fun handleTodosResponse(responseObject: List<TodoResponseModel>) {
        _todos.value = responseObject
//        todos = responseObject
//        t odosObservable.value = TodosState.Retrieved(todos)
    }
}