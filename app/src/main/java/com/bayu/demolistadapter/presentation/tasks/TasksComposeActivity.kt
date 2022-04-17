package com.bayu.demolistadapter.presentation.tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bayu.demolistadapter.presentation.ui.theme.DemoListAdapterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoListAdapterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "Hello Compose!", style = MaterialTheme.typography.h4)
                    }
                }
            }
        }
    }
}