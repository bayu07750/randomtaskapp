package com.bayu.demolistadapter.domain.task

import com.bayu.demolistadapter.core.data.utils.Sorting
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun add(task: Task)

    suspend fun update(task: Task)

    suspend fun delete(task: Task)

    fun getTasks(sorting: Sorting): Flow<List<Task>>

    fun getTasksByTitle(sorting: Sorting, query: String): Flow<List<Task>>

    fun getLatestTasksByTitle(query: String): Flow<List<Task>>

    fun getOldestTasksByTitle(query: String): Flow<List<Task>>

}