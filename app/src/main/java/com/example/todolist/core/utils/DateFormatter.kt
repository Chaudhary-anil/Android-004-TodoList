package com.example.todolist.core.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {

    @SuppressLint("ConstantLocale", "WeekBasedYear")
    val dateFormat = SimpleDateFormat("DD MMM YYYY", Locale.getDefault())
    @SuppressLint("ConstantLocale")
    val timeFormat = SimpleDateFormat("HH:MM", Locale.getDefault())

    fun format(epoch: Long): String = dateFormat.format(Date(epoch))
    fun formatTime(epoch: Long): String = timeFormat.format(Date(epoch))

}