package com.toutunprog.todoprog.model

data class TodoList(val index: Int, val title: String, val items: Array<TodoItem>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TodoList

        if (title != other.title) return false
        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + items.contentHashCode()
        return result
    }
}