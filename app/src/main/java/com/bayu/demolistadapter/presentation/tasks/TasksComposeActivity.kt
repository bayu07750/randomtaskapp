package com.bayu.demolistadapter.presentation.tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.bayu.demolistadapter.presentation.tasks.components.TasksScreen
import com.bayu.demolistadapter.presentation.ui.theme.DemoListAdapterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksComposeActivity : ComponentActivity() {

    private val viewModel: TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoListAdapterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TasksScreen(viewModel)
                }
            }
        }
    }
}