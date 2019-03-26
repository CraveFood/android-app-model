package com.cravefood.androidappmodel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravefood.androidappmodel.R

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
//            .add(R.id.fragmentContainer, TodoListFragment.newInstance())
            .commit()
    }



}
