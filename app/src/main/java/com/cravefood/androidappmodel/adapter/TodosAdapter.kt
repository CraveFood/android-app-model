package com.cravefood.androidappmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.databinding.ListItemTodoBinding
import com.cravefood.androidappmodel.ui.TodosViewModel

class TodosAdapter(
    private val viewModel: TodosViewModel,
    private val list: MutableList<TodoResponseModel> = arrayListOf()
) : RecyclerView.Adapter<TodosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_todo, parent, false))
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemTodoBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.list_item_todo, parent, false
        )

        return ViewHolder(binding)


    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TodosAdapter.ViewHolder, position: Int) =
        holder.bind(list[position])

    fun updateList(newList: List<TodoResponseModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val listItemDataBinding: ListItemTodoBinding) :
        RecyclerView.ViewHolder(listItemDataBinding.root) {
        fun bind(item: TodoResponseModel) = with(itemView) {
            listItemDataBinding.item = item
            listItemDataBinding.executePendingBindings()

//            viewModel.onClickTodo(item, itemView)
            /*if (onClickListener != null) {
                layoutParent.isClickable = true
                layoutParent.setOnClickListener {
                    onClickListener.invoke(item)
                }
            } else {
                layoutParent.setOnClickListener(null)
                layoutParent.isClickable = false
            }*/
        }
    }
}