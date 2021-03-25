package com.ruichaoqun.luckymusicv2.data.source

import androidx.lifecycle.LiveData
import com.ruichaoqun.luckymusicv2.data.Task
import com.ruichaoqun.luckymusicv2.data.Result

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 16:39
 * @Description:    DataSource
 * @Version:        1.0
 */
interface DataSource {
    suspend fun refreshTasks()
    suspend fun getTasks(): Result<List<Task>>
    suspend fun deleteAllTasks()
    suspend fun saveTask(task: Task)
    fun observeTasks(): LiveData<Result<List<Task>>>
    suspend fun getTask(taskId: String):Result<Task>
    suspend fun completeTask(task: Task)
    suspend fun acrivateTask(task: Task)

}