package com.ruichaoqun.luckymusicv2.data

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/30 10:11
 * @Description:    BaseResponse
 * @Version:        1.0
 */
data class BaseResponse<T>(var data:T,var errorCode:Int,var errorMsg:String)