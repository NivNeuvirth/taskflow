package com.niv.todolistapp.ui.task_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

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
            items(
                items = tasks,
                key = { it.id }
            ) { task ->

                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { update ->
                        if (update == SwipeToDismissBoxValue.EndToStart) {
                            // If swiped Right -> Left, delete it
                            viewModel.onTaskDelete(task)
                            true
                        } else {
                            false
                        }
                    }
                )

                // 2. The Swipe Component
                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {
                        // The Red Background with Trash Icon
                        val color =
                            if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                                Color.Red
                            } else {
                                Color.Transparent
                            }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(16.dp),
                            contentAlignment = Alignment.CenterEnd // Icon on the right
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White
                            )
                        }
                    },
                    enableDismissFromStartToEnd = false // Disable Left -> Right swipe
                ) {
                    // 3. Your Original Item
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
}