package com.bayu.demolistadapter.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayu.demolistadapter.R
import com.bayu.demolistadapter.core.data.utils.SORTING
import com.bayu.demolistadapter.databinding.FragmentTasksBinding
import com.bayu.demolistadapter.domain.task.Task
import com.bayu.demolistadapter.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class TasksFragment : BaseFragment<FragmentTasksBinding>() {

    private val viewModel: TasksViewModel by viewModels()

    private lateinit var tasksAdapter: TasksAdapter

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTasksBinding {
        return FragmentTasksBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observe()
        actions()
    }

    private fun actions() {
        val menuItem = binding.toolbar.menu.findItem(R.id.searchTask)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Search Task"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setQueryTask(newText ?: "")
                return true
            }
        })
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.sortLatest -> {
                    it.isChecked = true
                    viewModel.setTasksSorting(SORTING.LATEST)
                    true
                }
                R.id.sortOldest -> {
                    it.isChecked = true
                    viewModel.setTasksSorting(SORTING.OLDEST)
                    true
                }
                else -> false
            }
        }
        binding.fabAddRandomTask.setOnClickListener {
            viewModel.addTask(Task(title = "Task ${Random.nextInt(from = 1, until = 1000)}"))
        }
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tasksUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    val (isLoading, tasks) = it
                    when {
                        isLoading -> showUiLoading()
                        !isLoading && tasks.isNotEmpty() -> {
                            showUiTasks()
                            tasksAdapter.submitList(tasks)
                        }
                        !isLoading && tasks.isEmpty() -> showUiEmptyTasks()
                    }
                }
        }
    }

    private fun showUiLoading() {
        with(binding) {
            pbLoadingTasks.isVisible = true
            rvTasks.isVisible = false
            tvEmptyTasks.isVisible = false
        }
    }

    private fun showUiTasks() {
        with(binding) {
            pbLoadingTasks.isVisible = false
            rvTasks.isVisible = true
            tvEmptyTasks.isVisible = false
        }
    }

    private fun showUiEmptyTasks() {
        with(binding) {
            pbLoadingTasks.isVisible = false
            rvTasks.isVisible = false
            tvEmptyTasks.isVisible = true
        }
    }

    private fun initView() {
        tasksAdapter = TasksAdapter()
        with(binding.rvTasks) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tasksAdapter
        }
    }
}