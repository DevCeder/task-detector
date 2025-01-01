package com.devceder.taskdetector.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val priority: Priority = Priority.NORMAL,
    val dueDate: Long? = null,
    val isComplete: Boolean = false
)

enum class Priority { LOW, NORMAL, HIGH }