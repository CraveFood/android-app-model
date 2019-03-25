package com.cravefood.androidappmodel.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cravefood.androidappmodel.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTodos.setOnClickListener {
            startActivity(Intent(this, TodosActivity::class.java))
        }
        buttonTodo.setOnClickListener {
            startActivity(Intent(this, TodoActivity::class.java))
        }
    }
}
