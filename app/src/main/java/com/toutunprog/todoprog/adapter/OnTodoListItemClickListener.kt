package com.toutunprog.todoprog.adapter

import com.toutunprog.todoprog.model.TodoList

interface OnTodoListItemClickListener {
	fun onTodoListItemClick(todoListItem: TodoList)
	fun onTodoListItemLongClick(todoListItem: TodoList)
}