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
	private val onTodoItemClickListener: OnTodoItemClickListener
) : RecyclerView.Adapter<TodoItemAdapter.ViewHolder>() {

	companion object {
		private var onBind = false
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		val textView = view.findViewById<TextView>(R.id.textView)
		val checkBox = view.findViewById<CheckBox>(R.id.checkbox)

		fun bind(
			todoItem: TodoItem,
			onTodoItemClickListener: OnTodoItemClickListener
		) {
			checkBox.setOnCheckedChangeListener(null)
			textView.text = todoItem.text
			checkBox.isChecked = todoItem.done
			println("Bind todoItem $todoItem")
			setTextViewDoneStatus(todoItem.done)
			checkBox.setOnCheckedChangeListener { _, isChecked ->

				println("check changed on todoItem $todoItem")
				todoItem.done = isChecked
				setTextViewDoneStatus(todoItem.done)
				if (onBind) {
					return@setOnCheckedChangeListener
				}
				onTodoItemClickListener.onTodoItemChangeDoneStatus(todoItem)

			}
			itemView.setOnClickListener { checkBox.isChecked = !checkBox.isChecked }
			itemView.setOnLongClickListener {
				onTodoItemClickListener.onTodoItemLongClick(todoItem)
				return@setOnLongClickListener true
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
		holder.bind(myDataset[position], onTodoItemClickListener)
		onBind = false
	}


	fun updateData(dataset: Array<TodoItem>) {
		println("updateData todoItems = ${dataset}")
		myDataset = dataset
		notifyDataSetChanged()
	}


}