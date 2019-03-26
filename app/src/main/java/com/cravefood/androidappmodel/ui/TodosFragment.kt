package com.cravefood.androidappmodel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.adapter.TodosAdapter
import com.cravefood.androidappmodel.databinding.FragmentTodosBinding
import org.koin.android.viewmodel.ext.android.viewModel


class TodosFragment : Fragment() {

    private lateinit var binding: FragmentTodosBinding
    private val viewModel by viewModel<TodosViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todos, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureComponents()
        loadData()
    }

    private fun configureComponents() {
        binding.recyclerViewTodos.apply {
            //            val customLayoutManager = LinearLayoutManager(context)
//            val dividerItemDecoration = DividerItemDecoration(context, customLayoutManager.orientation)
//
//            addItemDecoration(dividerItemDecoration)
//            layoutManager = customLayoutManager
//            setHasFixedSize(true)

            adapter = TodosAdapter(viewModel)
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
