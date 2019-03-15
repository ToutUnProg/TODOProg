package com.toutunprog.todoprog.adapter

import com.toutunprog.todoprog.model.TodoItem

interface OnTodoItemChangeDoneStatusListener {
	fun onTodoItemChangeDoneStatus(todoItem: TodoItem)
}