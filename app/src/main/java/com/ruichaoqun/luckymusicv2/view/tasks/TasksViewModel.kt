package com.ruichaoqun.luckymusicv2.view.tasks

import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ruichaoqun.luckymusicv2.Event
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
            if(forceUpdate)
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

    private val _toast = MutableLiveData<String>()
    val toast:LiveData<String> = _toast

    private val _newTaskEvent = MutableLiveData<Event<Unit>>()
    val newTaskEvent: LiveData<Event<Unit>> = _newTaskEvent

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
        return if (tasksResult is Result.Success) {
            filterItems(tasksResult.data, getSavedFilterType())
        } else {
            emptyList()
        }
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

    fun refresh(){
        _forceUpdate.value = true
    }

    fun onCheckedChanged(task:Task,isComplete:Boolean){
        viewModelScope.launch {
            if(isComplete){
                appRepository.completeTask(task)
                _toast.value = "关闭活动"
                _forceUpdate.value = false
            }else{
                appRepository.acrivateTask(task)
                _toast.value = "开启活动"
                _forceUpdate.value = false
            }
        }
    }

    fun showDetail(task:Task){
        Log.w("AAAAA",task.id)
    }

    fun addNewTask(){
        _newTaskEvent.value = Event(Unit)
    }
}

const val TASKS_FILTER_SAVED_STATE_KEY = "TASKS_FILTER_SAVED_STATE_KEY"