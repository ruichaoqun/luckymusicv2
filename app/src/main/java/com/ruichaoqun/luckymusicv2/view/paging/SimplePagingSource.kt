package com.ruichaoqun.luckymusicv2.view.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruichaoqun.luckymusicv2.data.BaseResponse
import com.ruichaoqun.luckymusicv2.data.HomeListResponse
import kotlinx.coroutines.delay
import java.lang.Exception

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/29 15:17
 * @Description:    SimplePagingSource
 * @Version:        1.0
 */
abstract class SimplePagingSource<T,R>:PagingSource<Int,LoadMoreDataBean<R>>(){
    val INIT_PAGE_NUMBER = 0

    override fun getRefreshKey(state: PagingState<Int, LoadMoreDataBean<R>>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LoadMoreDataBean<R>> {
        val position = params.key ?: 0
        try {
            val response = remoteLoad(position)
            delay(2000)
            if(response.errorCode != 0){
                return LoadResult.Error(Throwable(response.errorMsg))
            }
            val list = getList(response)
            val preKey = if(hasPrePage(list,position)){
                position - 1
            }else{
                null
            }

            val nextKey = if(hasNextPage(list,position,response)){
                position+1
            }else{
                null
            }
            return LoadResult.Page(data = transFormData(list,showNoMoreItem(list,position,response)),prevKey = preKey,nextKey = nextKey)
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
    abstract fun getList(response: BaseResponse<T>): List<R>?

    /**
     * 是否有上一页 true是  false否  默认以0位第一页
     * @param list List<R>?
     * @param position Int
     * @return Boolean
     */
    protected fun hasPrePage(list: List<R>?, position: Int): Boolean {
        return position > 0
    }

    /**
     * 是否有下一页，true是  false否
     * @param list List<R>?  数据集合
     * @param position Int 当前页数
     * @param response BaseResponse<T> 本次请求返回的实体类
     * @return Boolean
     */
    abstract fun hasNextPage(list: List<R>?, position: Int,response:BaseResponse<T>): Boolean

    /**
     * 是否没有更多数据了（与是否有下一页不同，如果第一页也为空，应不展示，其他跟）
     * @return Boolean
     */
    private fun showNoMoreItem(list: List<R>?, position: Int,response:BaseResponse<T>): Boolean{
        //如果第一页也为空，应不展示
        if(list.isNullOrEmpty() && position == INIT_PAGE_NUMBER){
            return false
        }
        //否则跟是否有下一页逻辑相反
        return !hasNextPage(list,position,response)
    }

    private fun transFormData(repos: List<R>?, showNoMoreItem: Boolean): List<LoadMoreDataBean<R>> {
        if(repos.isNullOrEmpty()){
            return arrayListOf(LoadMoreDataBean(null,1))
        }
        val result:ArrayList<LoadMoreDataBean<R>> = arrayListOf()
        for (data in repos){
            result.add(LoadMoreDataBean(data,0))
        }
        if(showNoMoreItem){
            result.add(LoadMoreDataBean(null,1))
        }
        return result
    }
}

data class LoadMoreDataBean<R>(var value:R?,var type:Int)