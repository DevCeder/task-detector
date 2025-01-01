package com.devceder.taskdetector.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.devceder.taskdetector.data.local.TaskDatabase
import com.devceder.taskdetector.data.repository.TaskRepository
import com.devceder.taskdetector.data.repository.TaskRepositoryImpl
import com.devceder.taskdetector.util.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLogger(): Logger = Logger()

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            "task_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        db: TaskDatabase,
        logger: Logger
    ): TaskRepository = TaskRepositoryImpl(db.taskDao(), logger)
}