package com.toutunprog.todoprog.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoList(val index: Int, val title: String, val items: Array<TodoItem>) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as TodoList

		if (index != other.index) return false
		if (title != other.title) return false
		if (!items.contentEquals(other.items)) return false

		return true
	}

	override fun hashCode(): Int {
		var result = index
		result = 31 * result + title.hashCode()
		result = 31 * result + items.contentHashCode()
		return result
	}
}