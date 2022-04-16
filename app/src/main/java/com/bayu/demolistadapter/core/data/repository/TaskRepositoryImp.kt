package com.bayu.demolistadapter.core.data.repository

import com.bayu.demolistadapter.core.data.source.local.TaskLocalDataSource
import com.bayu.demolistadapter.core.data.utils.Sorting
import com.bayu.demolistadapter.core.data.utils.TaskMapper.Companion.toDomain
import com.bayu.demolistadapter.core.data.utils.TaskMapper.Companion.toEntity
import com.bayu.demolistadapter.domain.task.Task
import com.bayu.demolistadapter.domain.task.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImp @Inject constructor(
    private val taskLocalDataSource: TaskLocalDataSource
) : TaskRepository {

    override suspend fun add(task: Task) {
        taskLocalDataSource.add(task.toEntity())
    }

    override suspend fun update(task: Task) {
        taskLocalDataSource.update(task.toEntity())
    }

    override suspend fun delete(task: Task) {
        taskLocalDataSource.delete(task.toEntity())
    }

    override fun getTasks(sorting: Sorting): Flow<List<Task>> {
        return when (sorting) {
            Sorting.LATEST -> taskLocalDataSource.getLatest()
                .map { items -> items.map { it.toDomain() } }
            Sorting.OLDEST -> taskLocalDataSource.getOldest()
                .map { items -> items.map { it.toDomain() } }
        }
    }

    override fun getTasksByTitle(sorting: Sorting, query: String): Flow<List<Task>> {
        return when (sorting) {
            Sorting.LATEST -> getLatestTasksByTitle(query)
            Sorting.OLDEST -> getOldestTasksByTitle(query)
        }
    }

    override fun getLatestTasksByTitle(query: String): Flow<List<Task>> {
        return taskLocalDataSource.getLatestTasksByTitle(query)
            .map { items -> items.map { it.toDomain() } }
    }

    override fun getOldestTasksByTitle(query: String): Flow<List<Task>> {
        return taskLocalDataSource.getOldestTasksByTitle(query)
            .map { items -> items.map { it.toDomain() } }
    }
}