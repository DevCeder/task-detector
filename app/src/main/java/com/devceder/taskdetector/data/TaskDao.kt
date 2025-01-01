package com.devceder.taskdetector.data

import androidx.room.*
import com.devceder.taskdetector.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY dueDate ASC NULLS LAST")
    fun getTasks(): Flow<List<Task>>
    
    @Insert
    suspend fun insert(task: Task): Long
    
    @Update
    suspend fun update(task: Task)
    
    @Delete
    suspend fun delete(task: Task)
    
    @Query("SELECT * FROM tasks WHERE title LIKE :query OR description LIKE :query")
    fun search(query: String): Flow<List<Task>>
}