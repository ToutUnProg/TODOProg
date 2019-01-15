package com.toutunprog.todoprog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.toutunprog.todoprog.adapter.TodoItemAdapter
import com.toutunprog.todoprog.repository.TodoListRepository
import kotlinx.android.synthetic.main.activity_detail.*

class TodoDetailActivity : AppCompatActivity() {
    companion object {

        private const val INTENT_TODOLIST_ID = "INTENT_TODOLIST_ID"

        fun createIntent(context: Context, todoListIndex: Int): Intent {
            return Intent(context, TodoDetailActivity::class.java).apply {
                putExtra(INTENT_TODOLIST_ID, todoListIndex)
            }
        }
    }

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val todoListIndex = intent.getIntExtra(INTENT_TODOLIST_ID, -1)
        if (todoListIndex == -1) {
            throw IllegalArgumentException("No $INTENT_TODOLIST_ID provided, please use TodoDetailActivity.createIntent")
        }

        println("TodoDetailActivity created for list index : $todoListIndex")

        viewManager = LinearLayoutManager(this)
        val todoList = TodoListRepository().getTodoList(todoListIndex)
        title = todoList.title
        viewAdapter = TodoItemAdapter(todoList.items)

        detail_todos_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }
}