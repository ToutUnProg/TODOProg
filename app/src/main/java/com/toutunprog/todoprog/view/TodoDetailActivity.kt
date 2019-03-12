package com.toutunprog.todoprog.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.toutunprog.todoprog.R
import com.toutunprog.todoprog.TodoApplication
import com.toutunprog.todoprog.adapter.TodoItemAdapter
import com.toutunprog.todoprog.model.TodoItem
import com.toutunprog.todoprog.model.TodoList
import com.toutunprog.todoprog.view.uicomponents.AlertDialogBuilder
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.contentView

class TodoDetailActivity : AppCompatActivity() {
	companion object {

		private const val INTENT_TODOLIST_ID = "INTENT_TODOLIST_ID"

		fun createIntent(context: Context, todoListIndex: Int): Intent {
			return Intent(context, TodoDetailActivity::class.java).apply {
				putExtra(INTENT_TODOLIST_ID, todoListIndex)
			}
		}
	}

	private lateinit var viewAdapter: TodoItemAdapter
	private lateinit var viewManager: RecyclerView.LayoutManager
	private lateinit var todoList: TodoList

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)

		val todoListIndex = intent.getIntExtra(INTENT_TODOLIST_ID, -1)
		if (todoListIndex == -1) {
			throw IllegalArgumentException("No $INTENT_TODOLIST_ID provided, please use TodoDetailActivity.createIntent")
		}

		println("TodoDetailActivity created for list index : $todoListIndex")

		viewManager = LinearLayoutManager(this)
		todoList = (application as TodoApplication).todoIRepository.getSingleByIndex(todoListIndex)
		title = todoList.title
		viewAdapter = TodoItemAdapter(todoList.items)

		detail_todos_recyclerview.apply {
			setHasFixedSize(true)
			layoutManager = viewManager
			adapter = viewAdapter
		}

		fabTodoItem.setOnClickListener { view ->
			contentView?.let {
				val dialogBuilder = AlertDialogBuilder(
					AnkoContext.create(this, it),
					R.string.create_todo_title,
					R.string.create_todo_description,
					R.string.create_todo_hint
				) { text ->
					val repo = (application as TodoApplication).todoIRepository
					val newItems = todoList.items.toMutableList()
					newItems.add(TodoItem(todoList.items.size + 1, text, false))
					val newTodoList = todoList.copy(items = newItems.toTypedArray())
					viewAdapter.updateData(newTodoList.items)
					repo.update(todoList, newTodoList)
					todoList = newTodoList
				}
				dialogBuilder.build().show()
			}
		}
	}
}