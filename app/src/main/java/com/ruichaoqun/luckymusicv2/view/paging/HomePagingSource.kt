package com.ruichaoqun.luckymusicv2.view.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruichaoqun.luckymusicv2.data.ApiService
import com.ruichaoqun.luckymusicv2.data.BaseResponse
import com.ruichaoqun.luckymusicv2.data.HomeListResponse
import kotlinx.coroutines.delay
import java.io.IOException
import java.lang.Exception

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

    override fun getList(response: BaseResponse<HomeListResponse.Data>): List<HomeListResponse.Data.Result>? {
        return response.data.datas
    }

    override fun hasNextPage(
        list: List<HomeListResponse.Data.Result>?,
        position: Int,
        response: BaseResponse<HomeListResponse.Data>
    ): Boolean {
        return response.data.curPage < response.data.pageCount
    }
}

