package com.toutunprog.todoprog.repository

import com.toutunprog.todoprog.model.TodoItem
import com.toutunprog.todoprog.model.TodoList

class TodoListRepository() {

    public fun getDataSet(): Array<TodoList> {
        val dataset = Array(5) { TodoItem("it = $it", false) }
        return Array(10) { TodoList("listTitle ($it)", dataset) }
    }
}