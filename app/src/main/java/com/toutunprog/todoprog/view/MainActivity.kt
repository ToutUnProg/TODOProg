package com.toutunprog.todoprog.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.toutunprog.todoprog.R
import com.toutunprog.todoprog.TodoApplication
import com.toutunprog.todoprog.adapter.OnTodoListItemClickListener
import com.toutunprog.todoprog.adapter.TodoListAdapter
import com.toutunprog.todoprog.model.TodoList
import com.toutunprog.todoprog.view.uicomponents.AlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.contentView

class MainActivity : AppCompatActivity() {

	private lateinit var viewAdapter: TodoListAdapter
	private lateinit var viewManager: RecyclerView.LayoutManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)

		fab.setOnClickListener { view ->
			contentView?.let {
				val dialogBuilder = AlertDialogBuilder(
					AnkoContext.create(this, it),
					R.string.create_todo_title,
					R.string.create_todo_description,
					R.string.create_todo_hint
				) { text ->
					val repo = (application as TodoApplication).todoIRepository
					repo.insert(
						TodoList(
							repo.getSize(), text, arrayOf()
						)
					)
					viewAdapter.updateData(repo.getAll())
				}
				dialogBuilder.build().show()
			}
		}

		viewManager = LinearLayoutManager(this)
		val dataset = (application as TodoApplication).todoIRepository.getAll()
		viewAdapter = TodoListAdapter(dataset, object : OnTodoListItemClickListener {
			override fun onTodoListItemClick(todoListItem: TodoList) {
				startActivity(
					TodoDetailActivity.createIntent(
						this@MainActivity,
						todoListItem.index
					)
				)
			}
		})

		main_todos_recyclerview.apply {
			setHasFixedSize(true)
			layoutManager = viewManager
			adapter = viewAdapter
		}
	}
}
