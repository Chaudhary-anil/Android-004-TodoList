package com.example.todolist.presentation.screen.todoAddEditScreen

sealed interface TodoAddEditScreenEvent {
    data class OnTitleChange(val title: String): TodoAddEditScreenEvent
    data class OnDescriptionChange(val description: String): TodoAddEditScreenEvent
    data class OnPriorityChange(val priority: Int): TodoAddEditScreenEvent
    data class OnCategoryChange(val category: String): TodoAddEditScreenEvent
    object OnSaveClicked: TodoAddEditScreenEvent
}