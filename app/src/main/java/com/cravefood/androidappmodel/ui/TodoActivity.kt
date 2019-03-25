package com.cravefood.androidappmodel.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.extensions.observe
import kotlinx.android.synthetic.main.activity_todo.*
import org.koin.android.ext.android.inject

class TodoActivity : AppCompatActivity() {

    private val viewModel by inject<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        configureObservables()
        configureComponents()
    }

    private fun configureObservables() {
        observe(viewModel.todoObservable, ::handleTodo)
    }

    private fun configureComponents() {
        buttonSearch.setOnClickListener {
            viewModel.getTodo(editTextId.text.toString().toInt())
        }
    }

    private fun handleTodo(state: TodoViewModel.TodoState) {
        progressBarIndeterminate.visibility = View.GONE
        when (state) {
            is TodoViewModel.TodoState.Retrieved -> {
                fillInformation(state.todo)
            }
            is TodoViewModel.TodoState.ErrorGettingItem -> {
                Toast.makeText(this, "Error: ${state.message}", Toast.LENGTH_SHORT).show()
            }
            is TodoViewModel.TodoState.ToolbarLoading -> {
                progressBarIndeterminate.visibility = View.VISIBLE
            }
        }
    }

    private fun fillInformation(item: TodoResponseModel) {
        textViewId.text = item.id?.toString() ?: "ERROR"
        textViewUserId.text = item.userId?.toString() ?: "ERROR"
        textViewTitle.text = item.title ?: "ERROR"
        textViewCompleted.text = if (item.completed == true) "completed" else "not completed"
    }
}
