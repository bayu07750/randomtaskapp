package com.bayu.demolistadapter.presentation.tasks.components

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import com.bayu.demolistadapter.domain.task.Task
import kotlin.random.Random

@Composable
fun ButtonAddTask(
    onClick: (Task) -> Unit,
) {
    ExtendedFloatingActionButton(
        text = { Text(text = "Add random task") },
        onClick = { onClick(Task(title = "Task ${Random.nextInt(from = 1, until = 1000)}")) },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add random Task"
            )
        },
    )
}