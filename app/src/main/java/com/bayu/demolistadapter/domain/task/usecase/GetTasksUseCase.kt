package com.bayu.demolistadapter.domain.task.usecase

import com.bayu.demolistadapter.core.data.utils.SORTING
import com.bayu.demolistadapter.domain.task.Task
import com.bayu.demolistadapter.domain.task.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(tasksSorting: Flow<SORTING>): Flow<List<Task>> {
        return tasksSorting.flatMapLatest { taskRepository.getTasks(it) }
    }
}