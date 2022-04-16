package com.bayu.demolistadapter.domain.task.usecase

import com.bayu.demolistadapter.core.data.utils.SORTING
import com.bayu.demolistadapter.domain.task.Task
import com.bayu.demolistadapter.domain.task.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class SearchSortingTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(sorting: Flow<SORTING>, query: Flow<String>): Flow<List<Task>> {
        return combine(sorting, query) { a, b ->
            Pair(a, b)
        }.flatMapLatest { (sorting, query) ->
            if (query.isEmpty()) {
                taskRepository.getTasks(sorting)
            } else {
                taskRepository.getTasksByTitle(sorting, query)
            }
        }
    }
}