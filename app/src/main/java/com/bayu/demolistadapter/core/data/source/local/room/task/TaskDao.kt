package com.bayu.demolistadapter.core.data.source.local.room.task

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM task_table ORDER BY createAt DESC")
    fun getLatestTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_table ORDER BY createAt ASC")
    fun getOldestTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_table WHERE title LIKE '%' || :query || '%' ORDER BY createAt DESC")
    fun getLatestTasksByTitle(query: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_table WHERE title LIKE '%' || :query || '%' ORDER BY createAt ASC")
    fun getOldestTasksByTitle(query: String): Flow<List<TaskEntity>>

}