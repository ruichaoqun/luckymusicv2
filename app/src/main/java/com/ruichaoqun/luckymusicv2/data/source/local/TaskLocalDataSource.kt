package com.ruichaoqun.luckymusicv2.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.ruichaoqun.luckymusicv2.data.Result
import com.ruichaoqun.luckymusicv2.data.Task
import com.ruichaoqun.luckymusicv2.data.source.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Error
import java.lang.Exception

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 16:41
 * @Description:    TaskLocalDataSource
 * @Version:        1.0
 */
class TaskLocalDataSource(
    private var tasksDao:TaskDao,
    private val ioDispatcher:CoroutineDispatcher = Dispatchers.IO
):DataSource {
    override suspend fun refreshTasks() {
    }

    override suspend fun getTasks(): Result<List<Task>> = withContext(ioDispatcher){
        return@withContext try {
            Result.Success(tasksDao.getTasks())
        }catch (e:Exception){
            Result.Error(e.message?:"")
        }
    }


    override suspend fun deleteAllTasks() =  withContext(ioDispatcher) {
        tasksDao.deleteTasks()
    }


    override suspend fun saveTask(task: Task) = withContext(ioDispatcher){
        tasksDao.insertTask(task)
    }

    override fun observeTasks(): LiveData<Result<List<Task>>> {
        return tasksDao.observeTasks().map {
            Result.Success(it)
        }
    }
}