package com.toutunprog.todoprog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class TodoDetailActivity : AppCompatActivity() {
    companion object {

        private const val INTENT_TODOLIST_ID = "INTENT_TODOLIST_ID"

        fun createIntent(context: Context, todoListIndex: Int): Intent {
            return Intent(context, TodoDetailActivity::class.java).apply {
                putExtra(INTENT_TODOLIST_ID, todoListIndex)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todoListIndex = intent.getIntExtra(INTENT_TODOLIST_ID, -1)
        if (todoListIndex == -1) {
            throw IllegalArgumentException("No $INTENT_TODOLIST_ID provided, please use TodoDetailActivity.createIntent")
        }

        println("TodoDetailActivity created for list index : $todoListIndex")
    }
}