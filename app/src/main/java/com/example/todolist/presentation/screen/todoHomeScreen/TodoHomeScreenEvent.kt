package com.example.todolist.presentation.screen.todoHomeScreen

import com.example.todolist.data.local.table.Todo
import com.example.todolist.domain.model.Filter

sealed interface TodoHomeScreenEvent {
    data class SearchChanged(val query: String): TodoHomeScreenEvent
    data class FilterChanged(val filter: Filter): TodoHomeScreenEvent
    data class ToggleComplete(val todo: Todo): TodoHomeScreenEvent
    data class DeleteTodo(val todo: Todo): TodoHomeScreenEvent
    object ClearCompleted: TodoHomeScreenEvent
}