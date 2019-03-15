package com.toutunprog.todoprog.adapter

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.toutunprog.todoprog.R
import com.toutunprog.todoprog.model.TodoItem

class TodoItemAdapter(
	private var myDataset: Array<TodoItem>,
	private val onTodoItemChangeDoneStatusListener: OnTodoItemChangeDoneStatusListener
) : RecyclerView.Adapter<TodoItemAdapter.ViewHolder>() {

	private var onBind = false

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		val textView = view.findViewById<TextView>(R.id.textView)
		val checkBox = view.findViewById<CheckBox>(R.id.checkbox)

		fun bind(
			todoItem: TodoItem,
			onTodoItemChangeDoneStatusListener: OnTodoItemChangeDoneStatusListener,
			onBind: Boolean
		) {
			textView.text = todoItem.text
			checkBox.isChecked = todoItem.done
			setTextViewDoneStatus(todoItem.done)

			checkBox.setOnCheckedChangeListener { _, isChecked ->
				todoItem.done = isChecked
				setTextViewDoneStatus(todoItem.done)
				if (onBind) {
					return@setOnCheckedChangeListener
				}
				onTodoItemChangeDoneStatusListener.onTodoItemChangeDoneStatus(todoItem)
			}
		}

		private fun setTextViewDoneStatus(doneStatus: Boolean) {
			if (doneStatus) {
				textView.paintFlags = textView.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
			} else {
				textView.paintFlags = textView.paintFlags.and(Paint.STRIKE_THRU_TEXT_FLAG.inv())
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.todo_detail_item, parent, false)

		return ViewHolder(view)
	}

	override fun getItemCount() = myDataset.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		onBind = true
		holder.bind(myDataset[position], onTodoItemChangeDoneStatusListener, onBind)
		onBind = false
	}

	fun updateData(dataset: Array<TodoItem>) {
		myDataset = dataset
		notifyDataSetChanged()
	}


}