package com.devceder.taskdetector.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.devceder.taskdetector.data.TaskDao
import com.devceder.taskdetector.model.Task
import com.devceder.taskdetector.model.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {
    val tasks = taskDao.getTasks().asLiveData()
    
    fun addTask(
        title: String,
        description: String = "",
        priority: Priority = Priority.NORMAL,
        dueDate: Long? = null
    ) {
        if (title.isBlank()) return
        
        viewModelScope.launch {
            taskDao.insert(Task(
                title = title,
                description = description,
                priority = priority,
                dueDate = dueDate
            ))
        }
    }
    
    fun updateTask(task: Task) = viewModelScope.launch { taskDao.update(task) }
    fun deleteTask(task: Task) = viewModelScope.launch { taskDao.delete(task) }
}