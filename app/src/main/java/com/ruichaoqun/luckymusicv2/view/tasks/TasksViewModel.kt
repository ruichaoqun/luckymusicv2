package com.ruichaoqun.luckymusicv2.view.tasks

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ruichaoqun.luckymusicv2.R
import com.ruichaoqun.luckymusicv2.data.Result
import com.ruichaoqun.luckymusicv2.data.Task
import com.ruichaoqun.luckymusicv2.data.source.AppRepository
import com.scwang.smart.refresh.layout.api.RefreshLayout
import kotlinx.coroutines.launch

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/8 18:13
 * @Description:    TasksViewModel
 * @Version:        1.0
 */
class TasksViewModel @ViewModelInject constructor(
    private val appRepository: AppRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _forceUpdate = MutableLiveData<Boolean>()

    private val _items = _forceUpdate.switchMap { forceUpdate ->
        liveData {
            _dataLoading.value = true
            var tasks = appRepository.getAllTasks()
            _dataLoading.value = false
            emit(filterTask(tasks))
        }
    }

    val items:LiveData<List<Task>> = _items

    val empty:LiveData<Boolean> = _items.map {
        it.isEmpty()
    }

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _filterLabelString = MutableLiveData<String>()
    val filterLabelString: LiveData<String> = _filterLabelString

    private val _noTaskString = MutableLiveData<String>()
    val noTasksString: LiveData<String> = _noTaskString

    private val _noTasksDrawable = MutableLiveData<Int>()
    val noTasksDrawable: LiveData<Int> = _noTasksDrawable

    private val _taskAddVisible = MutableLiveData<Boolean>()
    val taskAddVisible: LiveData<Boolean> = _taskAddVisible

    init {
        setFiltering(getSavedFilterType())
        loadTasks(true)
    }

    private fun setFiltering(savedFilterType: TaskFilterType) {
        savedStateHandle.set(TASKS_FILTER_SAVED_STATE_KEY, savedFilterType)
        when (savedFilterType) {
            TaskFilterType.ALL_TASKS -> {
                setFilter("所有任务", "你还没有创建任务！", R.drawable.logo_no_fill, true)
            }
            TaskFilterType.ACTIVE_TASKS -> {
                setFilter("活动任务", "你还没有活动任务！", R.drawable.ic_check_circle_96dp, false)
            }
            TaskFilterType.COMPLETED_TASKS -> {
                setFilter("已完成任务", "你还没有已完成任务！", R.drawable.ic_verified_user_96dp, false)
            }
        }
        loadTasks(false)
    }

    private fun loadTasks(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun setFilter(
        filterLabelString: String,
        noTaskString: String,
        noTasksDrawable: Int,
        taskAddVisible: Boolean
    ) {
        _filterLabelString.value = filterLabelString
        _noTaskString.value = noTaskString
        _noTasksDrawable.value = noTasksDrawable
        _taskAddVisible.value = taskAddVisible
    }

    private fun getSavedFilterType(): TaskFilterType {
        return savedStateHandle.get(TASKS_FILTER_SAVED_STATE_KEY) ?: TaskFilterType.ALL_TASKS
    }

    private fun filterTask(tasksResult: Result<List<Task>>): List<Task> {
        val result = Result<List<Task>>()
        if (tasksResult is Result.Success) {
            viewModelScope.launch {
                result.value = filterItems(tasksResult.data, getSavedFilterType())
            }
        } else {
            result.value = emptyList()
        }
        return result
    }

    private fun filterItems(data: List<Task>, savedFilterType: TaskFilterType): List<Task> {
        val taskShow = ArrayList<Task>()
        for (task in data) {
            when (savedFilterType) {
                TaskFilterType.ALL_TASKS -> taskShow.add(task)
                TaskFilterType.ACTIVE_TASKS -> if (!task.isCompleted) {
                    taskShow.add(task)
                }
                TaskFilterType.COMPLETED_TASKS -> if (task.isCompleted) {
                    taskShow.add(task)
                }
            }
        }
        return taskShow
    }

    fun refresh(refreshLayout:RefreshLayout){
        _forceUpdate.value = true
    }

    fun loadMore(refreshLayout:RefreshLayout){

    }
}

const val TASKS_FILTER_SAVED_STATE_KEY = "TASKS_FILTER_SAVED_STATE_KEY"