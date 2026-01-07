package com.example.todolist.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.local.dao.TodoDao
import com.example.todolist.data.local.table.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}