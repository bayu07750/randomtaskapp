package com.bayu.demolistadapter.core.data.source.local

import com.bayu.demolistadapter.core.data.source.local.room.task.TaskDao
import com.bayu.demolistadapter.core.data.source.local.room.task.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskLocalDataSource @Inject constructor(
    private val taskDao: TaskDao,
) {

    suspend fun add(taskEntity: TaskEntity) {
        withContext(Dispatchers.IO) {
            taskDao.addTask(taskEntity)
        }
    }

    suspend fun update(taskEntity: TaskEntity) {
        withContext(Dispatchers.IO) {
            taskDao.updateTask(taskEntity)
        }
    }

    suspend fun delete(taskEntity: TaskEntity) {
        withContext(Dispatchers.IO) {
            taskDao.deleteTask(taskEntity)
        }
    }

    fun getLatest(): Flow<List<TaskEntity>> {
        return taskDao.getLatestTasks().flowOn(Dispatchers.IO)
    }

    fun getOldest(): Flow<List<TaskEntity>> {
        return taskDao.getOldestTasks().flowOn(Dispatchers.IO)
    }

    fun getLatestTasksByTitle(query: String): Flow<List<TaskEntity>> {
        return taskDao.getLatestTasksByTitle(query).flowOn(Dispatchers.IO)
    }

    fun getOldestTasksByTitle(query: String): Flow<List<TaskEntity>> {
        return taskDao.getOldestTasksByTitle(query).flowOn(Dispatchers.IO)
    }

}