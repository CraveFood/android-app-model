package com.cravefood.androidappmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.databinding.CellTodoBinding

class TodoListAdapter(
    private val todoList: MutableList<TodoResponseModel> = mutableListOf()
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cell_todo,
                parent,
                false
            )
        )
    }

    fun update(items: List<TodoResponseModel>) {
        todoList.clear()
        todoList.addAll(items)
        notifyDataSetChanged()
    }

    fun getItems(): List<TodoResponseModel> {
        return todoList
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    inner class ViewHolder(val cellTodoBinding: CellTodoBinding) : RecyclerView.ViewHolder(cellTodoBinding.root) {
        fun bind(item: TodoResponseModel) = with(itemView) {
            cellTodoBinding.todoCellItem = item
//            cellTodoBinding.executePendingBindings()
        }
    }
}