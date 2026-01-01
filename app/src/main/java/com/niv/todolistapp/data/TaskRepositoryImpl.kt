package com.niv.todolistapp.data

import com.niv.todolistapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {
    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return dao.getAllTasks()
    }

    override suspend fun getTaskById(id: Int): TaskEntity? {
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(task: TaskEntity) {
        dao.insertTask(task)
    }

    override suspend fun updateTask(task: TaskEntity) {
        dao.updateTask(task)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        dao.deleteTask(task)
    }
}