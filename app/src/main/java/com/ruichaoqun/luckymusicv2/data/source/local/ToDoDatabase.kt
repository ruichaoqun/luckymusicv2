package com.ruichaoqun.luckymusicv2.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ruichaoqun.luckymusicv2.data.Task

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 18:07
 * @Description:    ToDoDatabase
 * @Version:        1.0
 */
@Database(entities = [Task::class],version = 1,exportSchema = false)
abstract class ToDoDatabase:RoomDatabase() {
    abstract fun taskDao():TaskDao
}