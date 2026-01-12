package com.example.todolist.presentation.screen.todoAddEditScreen

import com.example.todolist.core.utils.DateFormatter

data class TodoAddEditScreenState(
    val title: String = "",
    val description: String = "",
    val priority: Int = 2,
    val category: String = "Personal",
    val dueDate: Long? = null,
    val dueTime: Long? = null
) {
    val dueDateText: String?
        get() = dueDate?.let { DateFormatter.format(epoch = it) }

    val dueTimeText: String?
        get() = dueTime?.let { DateFormatter.formatTime(epoch = it) }
}