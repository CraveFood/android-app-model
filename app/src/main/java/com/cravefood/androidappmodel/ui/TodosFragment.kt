package com.cravefood.androidappmodel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.adapter.TodoListAdapter
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.extensions.observe
import kotlinx.android.synthetic.main.fragment_todos.*
import org.koin.android.viewmodel.ext.android.viewModel

class TodosFragment : Fragment() {

    private val viewModel by viewModel<TodosViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
                Toast.makeText(context, "Error: ${state.message}", Toast.LENGTH_SHORT).show()
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

    private fun loadData() {
        viewModel.getTodos()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            TodosFragment().apply {
            }
    }
}
