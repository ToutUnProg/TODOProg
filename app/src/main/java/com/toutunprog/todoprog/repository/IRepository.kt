package com.toutunprog.todoprog.repository

interface IRepository<T> {
	fun getAll(): Array<T>
	fun getSingleByIndex(index: Int): T
	fun insert(entity: T)
	fun update(oldEntity: T, newEntity: T)
	fun delete(entity: T)
}