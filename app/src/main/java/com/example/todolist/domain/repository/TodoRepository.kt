package com.example.todolist.domain.repository

import com.example.todolist.data.local.table.Todo
import kotlinx.coroutines.flow.Flow

// yo vaneko contractor ho aafai kam khi pani gardaina but kam khojxa and fix garxa. kam garna xutyo vane warn garxa

interface TodoRepository {
    fun getAllTodos(): Flow<List<Todo>>
    fun getActiveTodos(): Flow<List<Todo>>
    fun getSearchTodos(query: String): Flow<List<Todo>>
    suspend fun insertTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
    suspend fun deleteCompletedTodo()
}