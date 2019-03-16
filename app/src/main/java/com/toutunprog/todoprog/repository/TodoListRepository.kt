package com.toutunprog.todoprog.repository

import android.content.Context
import com.toutunprog.todoprog.model.TodoList
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import kotlinx.serialization.stringify
import java.io.File

class TodoListRepository(private val context: Context) : IRepository<TodoList> {

	private val dataSet =
		getDataSet().toMutableList()

	override fun getAll(): Array<TodoList> {
		dataSet.sortBy { it.index }
		return dataSet.toTypedArray()
	}

	override fun getSingleByIndex(index: Int): TodoList {
		return dataSet.single { it.index == index }
	}

	override fun insert(entity: TodoList) {
		var newEntity = entity
		val filter = dataSet.filter { it.index == entity.index }
		if (filter.size == 1 || entity.index == -1) {
			dataSet.sortBy { it.index }
			newEntity = entity.copy(index = if (dataSet.size > 0) dataSet.last().index + 1 else 0)
		}
		dataSet.add(newEntity)
		saveToFile(newEntity)
	}

	override fun update(oldEntity: TodoList, newEntity: TodoList) {
		delete(oldEntity)
		insert(newEntity)

	}

	override fun delete(entity: TodoList) {
		dataSet.remove(entity)
		deleteFile(entity)
	}

	override fun getSize(): Int {
		return dataSet.size
	}

	@UseExperimental(ImplicitReflectionSerializer::class)
	private fun saveToFile(entity: TodoList) {
		val entityAsString = Json.unquoted.stringify(entity)
		entityFile(entity).writeText(entityAsString)
	}

	private fun deleteFile(entity: TodoList) {
		val entityFile = entityFile(entity)
		if (entityFile.exists()) {
			entityFile.delete()
		}
	}

	private fun entityFile(entity: TodoList): File {
		val folder = entityFolder()
		return File(folder, entity.index.toString())
	}

	private fun entityFolder(): File {
		val folder = File(context.filesDir, "entity")
		if (!folder.exists()) {
			folder.mkdir()
		}
		return folder
	}

	@UseExperimental(ImplicitReflectionSerializer::class)
	private fun getDataSet(): Array<TodoList> {
		val folder = entityFolder()
		val allFiles = folder.listFiles()
		val todos = mutableListOf<TodoList>()
		allFiles.forEach { file ->
			val entityText = file.readText()
			val todo = Json.unquoted.parse<TodoList>(entityText)
			todos.add(todo)
		}
		return todos.toTypedArray()
	}
}