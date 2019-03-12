package com.toutunprog.todoprog.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.toutunprog.todoprog.R
import com.toutunprog.todoprog.model.TodoItem

class TodoItemAdapter(
	private var myDataset: Array<TodoItem>
) : RecyclerView.Adapter<TodoItemAdapter.ViewHolder>() {

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		val textView: TextView = view as TextView

		fun bind(todoItem: TodoItem) {
			textView.text = todoItem.text

		}
	}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.todos_textview, parent, false)

		return ViewHolder(view)
	}

	override fun getItemCount() = myDataset.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(myDataset[position])

	}

	fun updateData(dataset: Array<TodoItem>) {
		myDataset = dataset
		notifyDataSetChanged()
	}

}