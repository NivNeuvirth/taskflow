package com.niv.todolistapp.ui.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niv.todolistapp.data.TaskEntity
import com.niv.todolistapp.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks: Flow<List<TaskEntity>> = repository.getAllTasks()

    fun onTaskCheckedChange(task: TaskEntity, isChecked: Boolean) {
        viewModelScope.launch {
            repository.updateTask(
                task.copy(isCompleted = isChecked)
            )
        }
    }

    fun onTaskDelete(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}