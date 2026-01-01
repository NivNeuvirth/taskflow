package com.niv.todolistapp.domain.repository

import com.niv.todolistapp.data.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks(): Flow<List<TaskEntity>>

    suspend fun getTaskById(id: Int): TaskEntity?

    suspend fun insertTask(task: TaskEntity)

    suspend fun updateTask(task: TaskEntity)

    suspend fun deleteTask(task: TaskEntity)
}