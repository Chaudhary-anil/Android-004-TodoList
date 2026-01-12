package com.example.todolist.presentation.screen.todoAddEditScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.table.Todo
import com.example.todolist.domain.useCase.InsertTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoAddEditScreenViewModel @Inject constructor(
    private val insertTodoUseCase: InsertTodoUseCase
) : ViewModel() {
    var state by mutableStateOf(TodoAddEditScreenState())
        private set


    fun onEvent(event: TodoAddEditScreenEvent) {
        when(event) {
            is TodoAddEditScreenEvent.OnCategoryChange -> onCategoryChange(category = event.category)
            is TodoAddEditScreenEvent.OnDescriptionChange -> onDescriptionChange(description = event.description)
            is TodoAddEditScreenEvent.OnPriorityChange -> onPriorityChange(priority = event.priority)
            TodoAddEditScreenEvent.OnSaveClicked -> onSaveBtnClicked()
            is TodoAddEditScreenEvent.OnTitleChange -> onTitleChange(title = event.title)
        }
    }

    private fun onSaveBtnClicked() {
        viewModelScope.launch {
            val todo = Todo(
                title = state.title,
                description = state.description,
                priority = state.priority,
                category = state.category,
                isCompleted = false,
                dueDate = state.dueDate
            )
            insertTodoUseCase(todo = todo)
        }
    }

    private fun onTitleChange(title: String) {
        state = state.copy(title = title)
    }

    private fun onPriorityChange(priority: Int) {
        state = state.copy(priority = priority)
    }

    private fun onDescriptionChange(description: String) {
        state = state.copy(description = description)
    }

    private fun onCategoryChange(category: String) {
        state = state.copy(category = category)
    }

}


































