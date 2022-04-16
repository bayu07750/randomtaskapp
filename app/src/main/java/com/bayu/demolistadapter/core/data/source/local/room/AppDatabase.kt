package com.bayu.demolistadapter.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bayu.demolistadapter.core.data.source.local.room.task.TaskDao
import com.bayu.demolistadapter.core.data.source.local.room.task.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}