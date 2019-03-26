package com.cravefood.androidappmodel.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.adapter.TodoListAdapter
import com.cravefood.androidappmodel.data.model.TodoResponseModel
import com.cravefood.androidappmodel.databinding.FragmentTodoListBinding
import kotlinx.android.synthetic.main.fragment_todo_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class TodoListFragment : Fragment() {

    private lateinit var binding: FragmentTodoListBinding
    private val viewModel by viewModel<TodoListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)
        binding.lifecycleOwner = this
        binding.adapt = TodoListAdapter(
            viewModel.todoList.value!!.toMutableList()
        )
        Log.d("Rafadebug", "1")
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureComponents()
    }

    private fun configureComponents() {
        Log.d("Rafadebug", "2")
        button_ok.setOnClickListener {
            binding.adapt?.update(
                mutableListOf<TodoResponseModel>().also {
                    it.addAll(binding.adapt!!.getItems())
                    it.add(TodoResponseModel(1, 2, "New", false))
                }
            )
        }
//        binding.recyclerViewTodoList.apply {
//            adapter =
//        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            TodoListFragment().apply {

            }
    }
}
