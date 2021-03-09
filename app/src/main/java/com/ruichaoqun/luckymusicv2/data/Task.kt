package com.ruichaoqun.luckymusicv2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 15:27
 * @Description:    Task
 * @Version:        1.0
 */
@Entity(tableName = "tasks")
data class Task @JvmOverloads constructor(
    @ColumnInfo(name = "title")var title:String = "",
    @ColumnInfo(name = "description")var description:String = "",
    @ColumnInfo(name = "isCompleted")var isCompleted:Boolean = false,
    @PrimaryKey @ColumnInfo(name = "id")var id:String = UUID.randomUUID().toString()
                ) {

}