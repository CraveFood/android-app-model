package com.cravefood.androidappmodel.extensions

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.cravefood.androidappmodel.data.model.TodoResponseModel

/*
*************************************
*************************************
* *                               * *
* *    DO NOT FOLLOW THIS FILE    * *
* *                               * *
*************************************
************
 */
object TodoListSetter {

    @BindingAdapter("app:rvdata")
    fun <T> setTodoListOnRV(rv: RecyclerView, todoItems: MutableLiveData<TodoResponseModel>) {

    }

}