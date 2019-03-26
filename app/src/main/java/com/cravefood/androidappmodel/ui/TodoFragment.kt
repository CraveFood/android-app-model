package com.cravefood.androidappmodel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.databinding.FragmentTodoBinding
import org.koin.android.viewmodel.ext.android.viewModel


class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding
    private val viewModel by viewModel<TodoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TodoFragment().apply {
            }
    }
}
