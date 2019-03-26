package com.cravefood.androidappmodel.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cravefood.androidappmodel.adapter.TodosAdapter
import com.cravefood.androidappmodel.data.model.TodoResponseModel

object TodosSetter {
    @JvmStatic
    @BindingAdapter("data")
    fun <T> setData(recyclerView: RecyclerView, items: List<TodoResponseModel>) {
        if (recyclerView.adapter is TodosAdapter) {
            (recyclerView.adapter as TodosAdapter).updateList(items)
        }
    }
}