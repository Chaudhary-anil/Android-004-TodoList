package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.local.DB_NAME
import com.example.todolist.data.local.dao.TodoDao
import com.example.todolist.data.local.database.TodoDatabase
import com.example.todolist.data.repository.TodoRepositoryImplementation
import com.example.todolist.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// yesko name DiModule pani dina sakinxa

/*
    hami yaha object rakhxam kinavane hamilai yo class ko eauta matra isntance neeed hunxa. if object rakhenam vane hilt le every injection ma yo file
    or class ko object banauna sakxa. jasle garda
    unnecessary memory consumption hunxa and confusion pani hunxa.
    Di/singleton ko concept pani break hunxa
 */

@Module // yo annotation le hilt lai vanxa. yo class/object dependency provide garxa vanera
@InstallIn(SingletonComponent::class)  // same instance live app vari use hunxa
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): TodoDatabase {
        return Room.databaseBuilder(ctx, TodoDatabase::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: TodoDatabase): TodoDao {
        return db.todoDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: TodoDao): TodoRepository {
        return TodoRepositoryImplementation(dao = dao)
    }

}
































































