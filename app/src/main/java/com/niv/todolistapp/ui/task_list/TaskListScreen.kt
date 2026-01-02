package com.niv.todolistapp.ui.task_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel = viewModel(),
    onTaskClick: (Int) -> Unit
) {

    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(tasks) { task ->
            TaskItem(
                task = task,
                onCheckedChange = { isChecked ->
                    viewModel.onTaskCheckedChange(task, isChecked)
                }
            )
        }
    }
}