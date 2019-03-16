package com.toutunprog.todoprog.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.toutunprog.todoprog.R
import com.toutunprog.todoprog.model.TodoList

class TodoListAdapter(
	private var myDataset: Array<TodoList>,
	private val onTodoListItemClickListener: OnTodoListItemClickListener
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		val textView: TextView = view.findViewById(R.id.textView) as TextView

		fun bind(todoList: TodoList, onTodoListItemClickListener: OnTodoListItemClickListener) {
			textView.text = todoList.title
			itemView.setOnClickListener { onTodoListItemClickListener.onTodoListItemClick(todoList) }
			itemView.setOnLongClickListener {
				onTodoListItemClickListener.onTodoListItemLongClick(todoList)
				return@setOnLongClickListener true
			}
		}
	}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.todo_item, parent, false)

		return ViewHolder(view)
	}

	override fun getItemCount() = myDataset.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(myDataset[position], onTodoListItemClickListener)
	}

	fun updateData(dataset: Array<TodoList>) {
		myDataset = dataset
		notifyDataSetChanged()
	}


}