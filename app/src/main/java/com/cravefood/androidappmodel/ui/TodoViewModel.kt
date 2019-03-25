package com.cravefood.androidappmodel.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.data.repository.TodoRepository
import com.cravefood.androidappmodel.util.RequestCallback

class TodoViewModel(
    private val todosRepository: TodoRepository
) : ViewModel() {

    val todoObservable = MutableLiveData<TodoState>()


    fun getTodo(id: Int) {
        todoObservable.value = TodoState.ToolbarLoading
        todosRepository.getTodo(id, object : RequestCallback<TodoResponseModel>() {

            override fun onRequestResponse(responseObject: TodoResponseModel) {
                todoObservable.value = TodoState.Retrieved(responseObject)
            }

            override fun onRequestError(throwable: Throwable?) {
                todoObservable.value =
                    TodoState.ErrorGettingItem(throwable?.message ?: "err")
            }

            override fun onNoInternetConnection(message: String) {
                todoObservable.value = TodoState.NotConnected
            }

        })
    }

    sealed class TodoState {
        data class Retrieved(val todo: TodoResponseModel) : TodoState()
        data class ErrorGettingItem(val message: String) : TodoState()
        object ToolbarLoading : TodoState()
        object NotConnected : TodoState()

    }
}