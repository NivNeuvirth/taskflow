package com.niv.todolistapp.ui.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niv.todolistapp.data.TaskEntity
import com.niv.todolistapp.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    fun addTask(title: String, description: String?) {
        if (title.isBlank()) return

        viewModelScope.launch {
            repository.insertTask(
                TaskEntity(
                    title = title,
                    description = description
                )
            )
            // future navigation or other actions
        }
    }
}