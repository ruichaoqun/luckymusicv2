package com.ruichaoqun.luckymusicv2.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ruichaoqun.luckymusicv2.data.Task
import com.ruichaoqun.luckymusicv2.data.source.DataSource
import com.ruichaoqun.luckymusicv2.data.Result
import kotlinx.coroutines.delay

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 16:40
 * @Description:    TaskRemoteDataSource
 * @Version:        1.0
 */
class TaskRemoteDataSource:DataSource {

    private var Tasks_SERVICE_DATA = LinkedHashMap<String,Task>(2)
    private val observableTasks = MutableLiveData<Result<List<Task>>>()


    init {
        addTask("123","456")
        addTask("222","2222")
    }

    private fun addTask(title: String, description: String) {
        val newTask = Task(title,description)
        Tasks_SERVICE_DATA[newTask.id] = newTask
    }

    private val remoteTasks = MutableLiveData<Result<List<Task>>>()

    override suspend fun refreshTasks() {
        remoteTasks.value = getTasks()
    }

    override suspend fun getTasks(): Result<List<Task>> {
        val tasks = Tasks_SERVICE_DATA.values.toList()
        delay(2000)
        return Result.Success(tasks)
    }

    override suspend fun deleteAllTasks() {
    }

    override suspend fun saveTask(task: Task) {
    }

    override fun observeTasks(): LiveData<Result<List<Task>>> {
        return observableTasks
    }
}