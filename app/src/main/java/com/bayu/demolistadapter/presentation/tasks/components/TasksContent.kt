package com.bayu.demolistadapter.presentation.tasks.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import com.bayu.demolistadapter.core.data.utils.Sorting
import com.bayu.demolistadapter.domain.task.Task

@Composable
fun TasksContent(
    scaffoldState: ScaffoldState,
    isShowMenu: Boolean,
    setIsShowMenu: (Boolean) -> Unit,
    sortingTask: Sorting,
    setSortingTask: (Sorting) -> Unit,
    addRandomTask: (Task) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SearchTopAppBar(
                isShowMenu = isShowMenu,
                setIsShowMenu = setIsShowMenu,
                sortingTask = sortingTask,
                setSortingTask = setSortingTask
            )
        },
        floatingActionButton = {
            ButtonAddTask(onClick = addRandomTask)
        },
    ) { innerPadding ->
        content(innerPadding)
    }
}