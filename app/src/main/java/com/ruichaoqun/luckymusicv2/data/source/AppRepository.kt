package com.ruichaoqun.luckymusicv2.data.source

import androidx.lifecycle.LiveData
import com.ruichaoqun.luckymusicv2.data.Result
import com.ruichaoqun.luckymusicv2.data.Task

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 15:28
 * @Description:    AppRepository
 * @Version:        1.0
 */
interface AppRepository {
    suspend fun refreshTasks()
    fun observeTasks():LiveData<Result<List<Task>>>
}