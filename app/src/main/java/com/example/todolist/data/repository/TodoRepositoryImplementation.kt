package com.example.todolist.data.repository

import com.example.todolist.data.local.dao.TodoDao
import com.example.todolist.data.local.table.Todo
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
    yo vaneko worker class ho. so hamilai concrete function chahinxa. tyo possible hunxa class ma. so hamile class banako object banxa vanera.
 */

class TodoRepositoryImplementation @Inject constructor(
    private val dao: TodoDao
): TodoRepository {
    override fun getAllTodos(): Flow<List<Todo>> {
        return dao.getAllTodos()
    }

    override fun getActiveTodos(): Flow<List<Todo>> {
        return dao.getActiveTodos()
    }

    override fun getSearchTodos(query: String): Flow<List<Todo>> {
        return dao.getSearchTodos(query = query)
    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo = todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        dao.updateTodo(todo = todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo = todo)
    }

    override suspend fun deleteCompletedTodo() {
        dao.deleteCompletedTodo()
    }
}
























