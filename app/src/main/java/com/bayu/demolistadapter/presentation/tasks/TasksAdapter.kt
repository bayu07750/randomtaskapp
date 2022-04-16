package com.bayu.demolistadapter.presentation.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bayu.demolistadapter.databinding.ItemTaskBinding
import com.bayu.demolistadapter.domain.task.Task
import javax.inject.Inject

class TasksAdapter @Inject constructor() : ListAdapter<Task, TasksAdapter.ViewHolder>(Task.taskDiffUtil) {

    class ViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            with(binding) {
                tvTitle.text = task.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }
}