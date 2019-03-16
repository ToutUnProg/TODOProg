package com.toutunprog.todoprog.view

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.toutunprog.todoprog.R
import com.toutunprog.todoprog.TodoApplication
import com.toutunprog.todoprog.adapter.OnTodoListItemClickListener
import com.toutunprog.todoprog.adapter.TodoListAdapter
import com.toutunprog.todoprog.model.TodoList
import com.toutunprog.todoprog.repository.IRepository
import com.toutunprog.todoprog.view.uicomponents.AlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_modify_todo.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.contentView

class MainActivity : AppCompatActivity() {

	private lateinit var viewAdapter: TodoListAdapter
	private lateinit var viewManager: RecyclerView.LayoutManager
	private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
	private lateinit var repo: IRepository<TodoList>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)
		bottomSheetBehavior = BottomSheetBehavior.from(design_bottom_sheet)
		repo = (application as TodoApplication).todoIRepository

		fab.setOnClickListener { view ->
			contentView?.let {
				val dialogBuilder = AlertDialogBuilder(
					AnkoContext.create(this, it),
					R.string.create_todo_title,
					R.string.create_todo_description,
					R.string.create_todo_hint
				) { text ->

					repo.insert(
						TodoList(
							-1, text, arrayOf()
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
			override fun onTodoListItemLongClick(todoListItem: TodoList) {
				val btSheetDialog = BottomSheetDialog(this@MainActivity)
				btSheetDialog.apply {
					setContentView(R.layout.bottom_sheet_modify_todo)
					setCanceledOnTouchOutside(true)
					delete_action.setOnClickListener {
						repo.delete(todoListItem)
						viewAdapter.updateData(repo.getAll())
						btSheetDialog.hide()
					}
					rename_action.setOnClickListener {
						val dialogBuilder = AlertDialogBuilder(
							AnkoContext.create(this@MainActivity, it),
							R.string.rename_todo_title,
							R.string.rename_todo_description,
							R.string.rename_todo_hint,
							todoListItem.title
						) { text ->
							repo.update(todoListItem, todoListItem.copy(title = text))
							viewAdapter.updateData(repo.getAll())
							btSheetDialog.hide()
						}
						dialogBuilder.build().show()
					}
					show()
				}

			}

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
