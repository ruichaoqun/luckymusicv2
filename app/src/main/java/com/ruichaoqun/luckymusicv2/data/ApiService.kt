package com.ruichaoqun.luckymusicv2.data

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/26 14:06
 * @Description:    ApiService
 * @Version:        1.0
 */
interface ApiService {
    @GET("/article/list/{page}/json")
    suspend fun getHomdList(@Path("page") page:Int):BaseResponse<HomeListResponse.Data>
}