package com.niv.todolistapp.ui.add_task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddTaskScreen(
    // Hilt will automatically find and provide the AddTaskViewModel here
    viewModel: AddTaskViewModel = hiltViewModel(),
    onSaveSuccess: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Task Title") }
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        Button(
            onClick = {
                viewModel.addTask(title, description)
                onSaveSuccess()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Save Task")
        }
    }
}