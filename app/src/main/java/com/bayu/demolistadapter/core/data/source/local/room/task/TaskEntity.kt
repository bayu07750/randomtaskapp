package com.bayu.demolistadapter.core.data.source.local.room.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayu.demolistadapter.domain.task.Task

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val createAt: Long = System.currentTimeMillis()
)