package com.niv.todolistapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.niv.todolistapp.ui.add_task.AddTaskScreen
import com.niv.todolistapp.ui.navigation.Route
import com.niv.todolistapp.ui.task_list.TaskListScreen
import com.niv.todolistapp.ui.theme.TaskFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Route.TaskList // <--- Type-Safe Start!
                    ) {

                        // Screen 1: Task List
                        composable<Route.TaskList> {
                            TaskListScreen(
                                onTaskClick = {
                                    // We will handle clicks later
                                },
                                onAddTaskClick = {
                                    // Navigate to Add Task
                                    navController.navigate(Route.AddTask)
                                }
                            )
                        }

                        // Screen 2: Add Task
                        composable<Route.AddTask> {
                            AddTaskScreen(
                                onSaveSuccess = {
                                    // Pop back to list when done
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}