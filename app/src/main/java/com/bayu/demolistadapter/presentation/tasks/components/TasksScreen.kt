package com.bayu.demolistadapter.presentation.tasks.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayu.demolistadapter.R
import com.bayu.demolistadapter.presentation.tasks.TasksViewModel

@Composable
fun TasksScreen(
    viewModel: TasksViewModel = hiltViewModel()
) {
    val taskUiState by viewModel.tasksUiState.collectAsState()
    val sortingTask by viewModel.tasksSorting.collectAsState()

    val scaffoldState = rememberScaffoldState()
    val (isShowMenu, setIsShowMenu) = remember { mutableStateOf(false) }

    TasksContent(
        scaffoldState = scaffoldState,
        isShowMenu = isShowMenu,
        setIsShowMenu = setIsShowMenu,
        sortingTask = sortingTask,
        setSortingTask = viewModel::setTasksSorting,
        addRandomTask = viewModel::addTask,
    ) { innerPadding ->
        val lazyListState = rememberLazyListState()

        if (taskUiState.tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_tasks_yet),
                    style = MaterialTheme.typography.h4
                )
            }
        } else {
            Tasks(
                lazyListState = lazyListState,
                items = taskUiState.tasks
            )
        }
    }
}