package com.bayu.demolistadapter.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayu.demolistadapter.core.data.utils.Sorting
import com.bayu.demolistadapter.domain.task.Task
import com.bayu.demolistadapter.domain.task.TaskRepository
import com.bayu.demolistadapter.domain.task.usecase.SearchSortingTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TasksUiState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val searchSortingTasksUseCase: SearchSortingTasksUseCase
) : ViewModel() {

    private val _tasksSorting = MutableStateFlow(Sorting.LATEST)
    private val _queryTask = MutableStateFlow("")

    private val _tasksUiState = MutableStateFlow(TasksUiState())
    val tasksUiState = _tasksUiState.asStateFlow()

    init {
        viewModelScope.launch {
            _tasksUiState.update { it.copy(isLoading = true) }

            searchSortingTasksUseCase(_tasksSorting, _queryTask).collectLatest { tasks ->
                _tasksUiState.update { it.copy(isLoading = false, tasks = tasks) }
            }
        }
    }

    fun setTasksSorting(sorting: Sorting) {
        _tasksSorting.update { sorting }
    }

    fun setQueryTask(query: String) {
        _queryTask.update { query }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.add(task)
        }
    }
}