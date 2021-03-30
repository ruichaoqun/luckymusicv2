package com.ruichaoqun.luckymusicv2.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ruichaoqun.luckymusicv2.data.HomeListResponse
import com.ruichaoqun.luckymusicv2.data.Result
import com.ruichaoqun.luckymusicv2.data.Task
import com.ruichaoqun.luckymusicv2.view.paging.LoadMoreDataBean
import kotlinx.coroutines.flow.Flow

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 15:28
 * @Description:    AppRepository
 * @Version:        1.0
 */
interface AppRepository {
    suspend fun getAllTasks():Result<List<Task>>

    suspend fun getTask(taskId:String):Result<Task>

    suspend fun saveTask(task: Task)

    suspend fun completeTask(task: Task)
    suspend fun acrivateTask(task: Task)

    fun getHomeList(): Flow<PagingData<LoadMoreDataBean<HomeListResponse.Data.Result>>>
}