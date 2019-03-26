package com.cravefood.androidappmodel.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.data.repository.TodoRepository

class TodoListViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todoViewObservable = MutableLiveData<TodoResponseModel>()
    var todoList: MutableLiveData<List<TodoResponseModel>> = MutableLiveData<List<TodoResponseModel>>().apply {
        value = mutableListOf(
            TodoResponseModel(1, 2, "Foo", false),
            TodoResponseModel(2, 3, "Bar", true),
            TodoResponseModel(3, 4, "r", true),
            TodoResponseModel(4, 5, "a", false),
            TodoResponseModel(5, 6, "f", true)
        )
    }

}