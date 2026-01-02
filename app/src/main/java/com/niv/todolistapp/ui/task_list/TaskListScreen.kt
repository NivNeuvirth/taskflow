package com.niv.todolistapp.ui.task_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel = hiltViewModel(),
    onTaskClick: (Int) -> Unit,
    onAddTaskClick: () -> Unit
) {

    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTaskClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues, // Apply scaffold padding
            modifier = Modifier.padding(16.dp)
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
}