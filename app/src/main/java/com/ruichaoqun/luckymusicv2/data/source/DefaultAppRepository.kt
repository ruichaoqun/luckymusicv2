package com.ruichaoqun.luckymusicv2.data.source

import androidx.lifecycle.LiveData
import com.ruichaoqun.luckymusicv2.data.Result
import com.ruichaoqun.luckymusicv2.data.Task
import com.ruichaoqun.luckymusicv2.data.source.local.TaskLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 15:29
 * @Description:    DefaultAppRepository
 * @Version:        1.0
 */
class DefaultAppRepository(
    private val tasksLocalDataSource:DataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
):AppRepository {

    override suspend fun getAllTasks():Result<List<Task>> {
        return tasksLocalDataSource.getTasks()
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        return tasksLocalDataSource.getTask(taskId)
    }

    override suspend fun saveTask(task: Task) {
        tasksLocalDataSource.saveTask(task)
    }

    override suspend fun completeTask(task: Task) {
        tasksLocalDataSource.completeTask(task)
    }

    override suspend fun acrivateTask(task: Task) {
        tasksLocalDataSource.acrivateTask(task)
    }
}