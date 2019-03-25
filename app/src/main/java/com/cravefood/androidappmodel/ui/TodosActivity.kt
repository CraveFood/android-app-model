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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos)

        configureFragment()
    }

    private fun configureFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, TodosFragment.newInstance())
            .commit()
    }



}
