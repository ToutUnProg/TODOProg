package com.toutunprog.todoprog

import android.app.Application
import com.toutunprog.todoprog.model.TodoItem
import com.toutunprog.todoprog.model.TodoList
import com.toutunprog.todoprog.repository.IRepository
import com.toutunprog.todoprog.repository.TodoListRepository

class TodoApplication : Application() {
	lateinit var todoIRepository: IRepository<TodoList>


	override fun onCreate() {
		super.onCreate()
		val todoListRepository = TodoListRepository(this)

//		todoListRepository.insert(
//			TodoList(
//				1, "todo1", arrayOf(
//					TodoItem(1, "todoItem1", false),
//					TodoItem(2, "todoItem2", false)
//				)
//			)
//		)

		todoIRepository = todoListRepository
	}
}