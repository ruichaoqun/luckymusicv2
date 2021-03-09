package com.ruichaoqun.luckymusicv2.data


/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 16:44
 * @Description:    Result
 * @Version:        1.0
 */
sealed class Result<out R> {
    data class Success<out T>(val data :T):Result<T>()
    data class Error(val message:String):Result<Nothing>()
    object Loading:Result<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[message=$message]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null