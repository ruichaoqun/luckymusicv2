package com.ruichaoqun.luckymusicv2.data.source

import androidx.lifecycle.LiveData
import com.ruichaoqun.luckymusicv2.data.Result
import com.ruichaoqun.luckymusicv2.data.Task
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
    private val tasksRemoteDataSource:DataSource,
    private val tasksLocalDataSource:DataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
):AppRepository {
    override suspend fun refreshTasks() {
        updateTasksFromRemoteDataSource()

    }

    override fun observeTasks(): LiveData<Result<List<Task>>> {
        return tasksLocalDataSource.observeTasks()
    }

    private suspend fun updateTasksFromRemoteDataSource() {
        var tasks = tasksRemoteDataSource.getTasks()
        if(tasks is Result.Success){
            tasksLocalDataSource.deleteAllTasks()
            tasks.data.forEach { task->
                tasksLocalDataSource.saveTask(task)
            }
        }else if(tasks is Result.Error){

        }
    }
}