package com.example.todolist.presentation.screen.todoHomeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.table.Todo
import com.example.todolist.domain.model.Filter
import com.example.todolist.domain.useCase.DeleteCompletedTodoUseCase
import com.example.todolist.domain.useCase.DeleteTodoUseCase
import com.example.todolist.domain.useCase.GetActiveTodosUseCase
import com.example.todolist.domain.useCase.GetSearchTodosUseCase
import com.example.todolist.domain.useCase.GetTodosUseCase
import com.example.todolist.domain.useCase.InsertTodoUseCase
import com.example.todolist.domain.useCase.ToggleCompleteUseCase
import com.example.todolist.domain.useCase.UpdateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoHomeScreenViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val getActiveTodosUseCase: GetActiveTodosUseCase,
    private val getSearchTodosUseCase: GetSearchTodosUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val deleteCompletedTodoUseCase: DeleteCompletedTodoUseCase,
    private val toggleCompleteUseCase: ToggleCompleteUseCase
) : ViewModel() {
    var state by mutableStateOf(TodoHomeScreenState())
        private set

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(10000)

            combine(
                getTodosUseCase(),
                searchQueryFlow(),
                filterFlow()
            ) { all, query, activeFilter ->
                applyFilter(all,query, activeFilter)
            }.collect { filtered ->
                state = state.copy(
                    todos = filtered,
                    isLoading = false,
                    error = null
                )
            }
        }
    }


    private fun searchQueryFlow(): kotlinx.coroutines.flow.Flow<String> = snapshotFlow {
        state.searchQuery
    }

    private fun filterFlow(): kotlinx.coroutines.flow.Flow<Filter> = snapshotFlow {
        state.filter
    }

    private fun applyFilter(
        list: List<Todo>,
        query: String,
        activeFilter: Filter
    ): List<Todo> {
        return list
            .filter { todo ->
                when(activeFilter) {
                    Filter.ALL -> true
                    Filter.ACTIVE -> !todo.isCompleted
                    Filter.COMPLETED -> todo.isCompleted
                }
            }
            .filter { todo ->
                todo.title.contains(query, ignoreCase = true) || todo.description.contains(query, true)
            }
    }



    fun onEvent(event: TodoHomeScreenEvent) {
        when (event) {
            TodoHomeScreenEvent.ClearCompleted -> clearCompleted()
            is TodoHomeScreenEvent.DeleteTodo -> deleteTodo(event.todo)
            is TodoHomeScreenEvent.FilterChanged -> filterChanged(filter = event.filter)
            is TodoHomeScreenEvent.SearchChanged -> searchChanged(query = event.query)
            is TodoHomeScreenEvent.ToggleComplete -> toggleComplete(todo = event.todo)
        }
    }

    private fun toggleComplete(todo: Todo) {
        viewModelScope.launch {
            toggleCompleteUseCase(todo = todo)
        }
    }

    private fun searchChanged(query: String) {
        viewModelScope.launch {
            state = state.copy(searchQuery = query)
        }
    }

    private fun filterChanged(filter: Filter) {
        viewModelScope.launch {
            state = state.copy(filter = filter)
        }
    }

    private fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo = todo)
        }
    }

    private fun clearCompleted() {
        viewModelScope.launch {
            deleteCompletedTodoUseCase()
        }
    }
}













































