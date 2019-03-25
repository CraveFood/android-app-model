package com.cravefood.androidappmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import kotlinx.android.synthetic.main.list_item_todo.view.*

class TodoListAdapter(
    private val list: MutableList<TodoResponseModel> = arrayListOf(),
    private var clickListener: ((todo: TodoResponseModel) -> Unit)? = null
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_todo, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TodoListAdapter.ViewHolder, position: Int) =
        holder.bind(list[position], clickListener)

    fun updateList(
        newList: List<TodoResponseModel>,
        clickListener: ((todo: TodoResponseModel) -> Unit)? = null
    ) {
        list.clear()
        list.addAll(newList)
        this.clickListener = clickListener
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TodoResponseModel, onClickListener: ((todo: TodoResponseModel) -> Unit)?) = with(itemView) {
            textViewName.text = item.title

            if (onClickListener != null) {
                layoutParent.isClickable = true
                layoutParent.setOnClickListener {
                    onClickListener.invoke(item)
                }
            } else {
                layoutParent.setOnClickListener(null)
                layoutParent.isClickable = false
            }
        }
    }
}