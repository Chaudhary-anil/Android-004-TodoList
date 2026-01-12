package com.example.todolist.domain.useCase

import com.example.todolist.data.local.table.Todo
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// one useCase = one action -> one class
// class le garda di garna sajilo and future scalibility hunxa

class GetTodosUseCase @Inject constructor(private val repository: TodoRepository) {
    operator fun invoke(): Flow<List<Todo>> = repository.getAllTodos()
}

class GetActiveTodosUseCase @Inject constructor(private val repository: TodoRepository) {
    operator fun invoke(): Flow<List<Todo>> = repository.getActiveTodos()
}

class GetSearchTodosUseCase @Inject constructor(private val repository: TodoRepository) {
    operator fun invoke(query: String): Flow<List<Todo>> {
        return repository.getSearchTodos(query = query)
    }
}

class InsertTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.insertTodo(todo = todo)
    }
}

class UpdateTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.updateTodo(todo = todo)
    }
}

class DeleteTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.deleteTodo(todo = todo)
    }
}

class DeleteCompletedTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke() {
        repository.deleteCompletedTodo()
    }
}

class ToggleCompleteUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.updateTodo(todo.copy(isCompleted = !todo.isCompleted))
    }
}














































