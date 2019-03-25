package com.cravefood.androidappmodel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.extensions.observe
import kotlinx.android.synthetic.main.fragment_todo.*
import org.koin.android.ext.android.inject

class TodoFragment : Fragment() {

    private val viewModel by inject<TodoViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureComponents()
        configureObservables()
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
                Toast.makeText(context, "Error: ${state.message}", Toast.LENGTH_SHORT).show()
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

    companion object {
        @JvmStatic
        fun newInstance() =
            TodoFragment().apply {
            }
    }
}
