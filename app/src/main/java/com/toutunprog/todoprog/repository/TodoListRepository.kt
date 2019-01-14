package com.toutunprog.todoprog.repository

import com.toutunprog.todoprog.model.TodoItem
import com.toutunprog.todoprog.model.TodoList

class TodoListRepository {

    fun getDataSet(): Array<TodoList> {
        return Array(10) { todoListIndex ->
            TodoList(
                todoListIndex,
                "listTitle ($todoListIndex)",
                Array(5) { todoItemIndex -> TodoItem("item $todoItemIndex form todoList $todoListIndex", false) })
        }
    }

    fun getTodoList(index: Int): TodoList {
        return getDataSet().first { index == it.index }
    }
}