package com.cravefood.androidappmodel.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.data.repository.TodoRepository
import com.cravefood.androidappmodel.util.RequestCallback

class TodosViewModel(
    private val todosRepository: TodoRepository
) : ViewModel() {

    private lateinit var todos: List<TodoResponseModel>
    val todosObservable = MutableLiveData<TodosState>()

    fun getTodos(forceUpdate: Boolean = false) {
        if (::todos.isInitialized && !forceUpdate) {
            handleTodosResponse(todos)
            return
        }

        todosObservable.value = TodosState.ToolbarLoading

        todosRepository.getTodos(object : RequestCallback<List<TodoResponseModel>>() {

            override fun onRequestResponse(responseObject: List<TodoResponseModel>) {
                handleTodosResponse(responseObject)
            }

            override fun onRequestError(throwable: Throwable?) {
                todosObservable.value =
                    TodosState.ErrorGettingList(throwable?.message ?: "err")
            }

            override fun onNoInternetConnection(message: String) {
                todosObservable.value = TodosState.NotConnected
            }

        })
    }

    private fun handleTodosResponse(responseObject: List<TodoResponseModel>) {
        todos = responseObject
        todosObservable.value = TodosState.Retrieved(todos)
    }


    sealed class TodosState {
        data class Retrieved(val todos: List<TodoResponseModel>) : TodosState()
        data class ErrorGettingList(val message: String) : TodosState()
        object ToolbarLoading : TodosState()
        object NotConnected : TodosState()

    }
}