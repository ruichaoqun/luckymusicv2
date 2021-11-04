package com.ruichaoqun.luckymusicv2.view.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruichaoqun.luckymusicv2.data.BaseResponse
import kotlinx.coroutines.delay
import java.lang.Exception

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/29 15:17
 * @Description:    SimplePagingSource
 * @Version:        1.0
 */
abstract class SimplePagingSource<T,R:Any>:PagingSource<Int,R>(){
    val INIT_PAGE_NUMBER = 0

    override fun getRefreshKey(state: PagingState<Int,R>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, R> {
        val position = params.key ?: INIT_PAGE_NUMBER
        try {
            val response = remoteLoad(position)
            delay(2000)
            if(response.errorCode != 0){
                return LoadResult.Error(Throwable(response.errorMsg))
            }
            val list = mapToList(response)

            val preKey = if(hasPrePage(list,position)){
                position - 1
            }else{
                null
            }

            val nextKey = if(!list.isNullOrEmpty() && hasNextPage(list!!,position,response)){
                position+1
            }else{
                null
            }
            return LoadResult.Page(list?: arrayListOf(),preKey,nextKey)
        }catch (exception: Exception){
            return LoadResult.Error(exception)
        }
    }

    /**
     * apiService
     * @return BaseResponse<T>
     */
    abstract suspend fun remoteLoad(position: Int): BaseResponse<T>

    /**
     *根据data返回list集合
     * @param response BaseResponse<T>
     * @return List<R>
     */
    abstract fun mapToList(response: BaseResponse<T>): List<R>?

    /**
     * 是否有上一页 true是  false否  默认以0位第一页
     * @param list List<R>?
     * @param position Int
     * @return Boolean
     */
    open fun hasPrePage(list: List<R>?, position: Int): Boolean {
        return position > 0
    }

    /**
     * 是否有下一页，true是  false否
     * @param list List<R>?  数据集合
     * @param position Int 当前页数
     * @param response BaseResponse<T> 本次请求返回的实体类
     * @return Boolean
     */
    abstract fun hasNextPage(list: List<R>, position: Int,response:BaseResponse<T>): Boolean
}
