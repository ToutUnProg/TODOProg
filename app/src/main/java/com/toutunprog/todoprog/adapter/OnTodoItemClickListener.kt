package com.toutunprog.todoprog.adapter

import com.toutunprog.todoprog.model.TodoItem

interface OnTodoItemClickListener {
	fun onTodoItemChangeDoneStatus(todoItem: TodoItem)
	fun onTodoItemLongClick(todoItem: TodoItem)
}