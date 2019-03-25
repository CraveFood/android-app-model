package com.cravefood.androidappmodel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravefood.androidappmodel.R

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        configureFragment()
    }

    private fun configureFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, TodoFragment.newInstance())
            .commit()
    }

}
