package com.ruichaoqun.luckymusicv2.view.paging

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import com.ruichaoqun.luckymusicv2.data.HomeListResponse
import com.ruichaoqun.luckymusicv2.data.source.AppRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/26 13:48
 * @Description:    PagingViewModel
 * @Version:        1.0
 */
class PagingViewModel  @ViewModelInject constructor(
    private val appRepository: AppRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :ViewModel() {
    private var _listData:MutableLiveData<PagingData<LoadMoreDataBean<HomeListResponse.Data.Result>>> = MutableLiveData()

    val listData:LiveData<PagingData<LoadMoreDataBean<HomeListResponse.Data.Result>>> = _listData
    init {
        viewModelScope.launch {
            appRepository.getHomeList().collectLatest {
                _listData.value = it
            }
        }
    }
}