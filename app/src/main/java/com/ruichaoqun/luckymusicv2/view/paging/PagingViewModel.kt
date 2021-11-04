package com.ruichaoqun.luckymusicv2.view.paging

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ruichaoqun.luckymusicv2.data.source.AppRepository

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
    val listData = appRepository.getHomeList()
}