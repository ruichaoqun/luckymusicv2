package com.ruichaoqun.luckymusicv2.view.paging

import com.ruichaoqun.luckymusicv2.data.ApiService
import com.ruichaoqun.luckymusicv2.data.BaseResponse
import com.ruichaoqun.luckymusicv2.data.HomeListResponse

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/26 14:57
 * @Description:    HomePagingSource
 * @Version:        1.0
 */
class HomePagingSource(private val apiService: ApiService):SimplePagingSource<HomeListResponse.Data,HomeListResponse.Data.Result>() {
    override suspend fun remoteLoad(position: Int): BaseResponse<HomeListResponse.Data> {
        return apiService.getHomdList(position)
    }

    override fun mapToList(response: BaseResponse<HomeListResponse.Data>): List<HomeListResponse.Data.Result>? {
        return null
    }

    override fun hasNextPage(
        list: List<HomeListResponse.Data.Result>,
        position: Int,
        response: BaseResponse<HomeListResponse.Data>
    ): Boolean {
        return false
    }
}

