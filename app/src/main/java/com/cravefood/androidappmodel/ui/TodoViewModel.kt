package com.cravefood.androidappmodel.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.data.repository.TodoRepository
import com.cravefood.androidappmodel.util.RequestCallback

class TodoViewModel(private val todosRepository: TodoRepository) : ViewModel() {

    val progressBarVisibility = MutableLiveData<Int>().also { it.value = View.GONE }
    val todoViewObservable = MutableLiveData<TodoResponseModel>()
    val id = MutableLiveData<String>()

    fun getTodo() {
        progressBarVisibility.value = View.VISIBLE
        todosRepository.getTodo(id.value!!.toInt(), object : RequestCallback<TodoResponseModel>() {

            override fun onRequestResponse(responseObject: TodoResponseModel) {
                progressBarVisibility.value = View.GONE
                todoViewObservable.value = responseObject
            }

            override fun onRequestError(throwable: Throwable?) {
                progressBarVisibility.value = View.GONE
            }

            override fun onNoInternetConnection(message: String) {
                progressBarVisibility.value = View.GONE
                // todoObservable.value = TodoState.NotConnected
            }

        })
    }
}