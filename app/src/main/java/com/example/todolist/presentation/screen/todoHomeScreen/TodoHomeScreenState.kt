package com.example.todolist.presentation.screen.todoHomeScreen

import com.example.todolist.data.local.table.Todo
import com.example.todolist.domain.model.Filter

data class TodoHomeScreenState(
    val todos: List<Todo> = emptyList(),
    val searchQuery: String = "",
    val filter: Filter = Filter.ALL,
    val isLoading: Boolean = false,
    val error: String? = null
    )
