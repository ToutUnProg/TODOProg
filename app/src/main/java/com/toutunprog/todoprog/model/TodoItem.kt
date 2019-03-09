package com.toutunprog.todoprog.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoItem(val index: Int, val text: String, val done: Boolean)