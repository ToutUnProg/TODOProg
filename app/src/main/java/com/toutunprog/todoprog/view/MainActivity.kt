package com.toutunprog.todoprog.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.toutunprog.todoprog.R
import com.toutunprog.todoprog.TodoApplication
import com.toutunprog.todoprog.adapter.OnTodoListItemClickListener
import com.toutunprog.todoprog.adapter.TodoListAdapter
import com.toutunprog.todoprog.model.TodoList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var viewAdapter: RecyclerView.Adapter<*>
	private lateinit var viewManager: RecyclerView.LayoutManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)

		fab.setOnClickListener { view ->
			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show()
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

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return when (item.itemId) {
			R.id.action_settings -> true
			else -> super.onOptionsItemSelected(item)
		}
	}
}
