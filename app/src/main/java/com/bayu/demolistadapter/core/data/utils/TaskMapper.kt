package com.bayu.demolistadapter.core.data.utils

import com.bayu.demolistadapter.core.data.source.local.room.task.TaskEntity
import com.bayu.demolistadapter.domain.task.Task

class TaskMapper {
    companion object {
        fun TaskEntity.toDomain(): Task {
            return Task(id, title, createAt)
        }
        fun Task.toEntity(): TaskEntity {
            return TaskEntity(id, title, createAt)
        }
    }
}