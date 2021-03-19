package com.ruichaoqun.luckymusicv2.di

import android.content.Context
import androidx.room.Room
import com.ruichaoqun.luckymusicv2.data.source.AppRepository
import com.ruichaoqun.luckymusicv2.data.source.DataSource
import com.ruichaoqun.luckymusicv2.data.source.DefaultAppRepository
import com.ruichaoqun.luckymusicv2.data.source.local.TaskLocalDataSource
import com.ruichaoqun.luckymusicv2.data.source.local.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 18:26
 * @Description:    AppModule
 * @Version:        1.0
 */
@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalTasksDataSource

    @Singleton
    @LocalTasksDataSource
    @Provides
    fun provideTasksLocalDataSource(
        database: ToDoDatabase,
        ioDispatcher: CoroutineDispatcher
    ):DataSource{
        return TaskLocalDataSource(database.taskDao(),ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context):ToDoDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java,
            "Tasks.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideTaskRepository(
        @AppModule.LocalTasksDataSource local:DataSource,
        dispatcher:CoroutineDispatcher
    ):AppRepository{
        return DefaultAppRepository(local,dispatcher)
    }
}