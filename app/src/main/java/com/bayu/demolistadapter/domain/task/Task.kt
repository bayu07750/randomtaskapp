package com.bayu.demolistadapter.domain.task

import androidx.recyclerview.widget.DiffUtil
import com.bayu.demolistadapter.core.data.source.local.room.task.TaskEntity

data class Task(
    val id: Int = 0,
    val title: String,
    val createAt: Long = System.currentTimeMillis(),
) {
    companion object {
        internal val taskDiffUtil = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }

        }
    }
}
