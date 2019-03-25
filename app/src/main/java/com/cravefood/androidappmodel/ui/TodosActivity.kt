package com.cravefood.androidappmodel.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.adapter.TodoListAdapter
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.extensions.observe
import kotlinx.android.synthetic.main.activity_todos.*
import org.koin.android.viewmodel.ext.android.viewModel

class TodosActivity : AppCompatActivity() {

    private val viewModel by viewModel<TodosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos)

        configureComponents()
        configureObservables()

        loadData()
    }


    private fun configureComponents() {
        recyclerViewTodos.apply {
            val customLayoutManager = LinearLayoutManager(context)
            val dividerItemDecoration = DividerItemDecoration(context, customLayoutManager.orientation)

            addItemDecoration(dividerItemDecoration)
            layoutManager = customLayoutManager
            setHasFixedSize(true)

            adapter = TodoListAdapter()
        }
    }

    private fun configureObservables() {
        observe(viewModel.todosObservable, ::handleTodoList)
    }

    private fun handleTodoList(state: TodosViewModel.TodosState) {
        progressBarIndeterminate.visibility = View.GONE
        when (state) {
            is TodosViewModel.TodosState.Retrieved -> {
                fillTodoList(state.todos)
            }
            is TodosViewModel.TodosState.ErrorGettingList -> {
                Toast.makeText(this, "Error: ${state.message}", Toast.LENGTH_SHORT).show()
            }
            is TodosViewModel.TodosState.ToolbarLoading -> {
                progressBarIndeterminate.visibility = View.VISIBLE
            }
        }
    }

    private fun fillTodoList(items: List<TodoResponseModel>) {
        recyclerViewTodos.visibility = View.VISIBLE
        recyclerViewTodos.adapter?.let { adapter ->
            adapter as TodoListAdapter

            adapter.updateList(items)
        }
    }

    private fun loadData(){
        viewModel.getTodos()
    }
}
