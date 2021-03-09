package com.ruichaoqun.luckymusicv2.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ruichaoqun.luckymusicv2.data.Task

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 18:06
 * @Description:    TaskDao
 * @Version:        1.0
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    suspend fun getTasks():List<Task>

    @Query("DELETE FROM tasks")
    suspend fun deleteTasks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task:Task)

    @Query("SELECT * FROM TASKS")
    fun observeTasks():LiveData<List<Task>>
}