package com.bayu.demolistadapter.di

import android.content.Context
import androidx.room.Room
import com.bayu.demolistadapter.core.data.source.local.room.AppDatabase
import com.bayu.demolistadapter.core.data.source.local.room.task.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_app_db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: AppDatabase): TaskDao = database.taskDao()

}