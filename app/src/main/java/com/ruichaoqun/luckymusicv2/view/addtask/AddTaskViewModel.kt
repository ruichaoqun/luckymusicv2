package com.ruichaoqun.luckymusicv2.view.addtask

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruichaoqun.luckymusicv2.data.Result
import com.ruichaoqun.luckymusicv2.data.Task
import com.ruichaoqun.luckymusicv2.data.source.AppRepository
import com.ruichaoqun.luckymusicv2.data.source.DefaultAppRepository
import kotlinx.coroutines.launch

class AddTaskViewModel @ViewModelInject constructor(
    private val appRepository: AppRepository
): ViewModel() {
    private val _dataLoading:MutableLiveData<Boolean> = MutableLiveData()
    val dataLoading:LiveData<Boolean> = _dataLoading

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage:LiveData<String> = _toastMessage

    private val _taskUpdate = MutableLiveData<Boolean>()
    val taskUpdate:LiveData<Boolean> = _taskUpdate

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()


    private var taskId:String ?= null
    private var isNewTask:Boolean = false
    private var isDataLoad:Boolean = false
    private var taskCompleted = false

    fun getTaskDetail(taskId:String?){
        if(_dataLoading.value == true){
            return
        }
        this.taskId = taskId
        if(taskId == null){
            isNewTask = true
            return
        }
        if(isDataLoad){
            return
        }
        isNewTask = false
        _dataLoading.value = true
        viewModelScope.launch {
            appRepository.getTask(taskId).let {
                if(it is Result.Success){
                    onTaskLoaded(it.data)
                }else{
                    onDataNotVailable()
                }
            }
        }
    }

    private fun onDataNotVailable() {
        _dataLoading.value = false
    }

    private fun onTaskLoaded(data: Task) {
        title.value = data.title
        description.value = data.description
        taskCompleted = data.isCompleted
        _dataLoading.value = false
        isDataLoad = false
    }

    fun saveTask(){
        var currentTitle = title.value
        var currentDescription = description.value
        if(currentTitle.isNullOrEmpty() || currentDescription.isNullOrEmpty()){
            _toastMessage.value = "活动不能为空"
        }
        if(taskId == null){
            createTask(Task(currentTitle!!,currentDescription!!))
        }else{
            updateTask(Task(currentTitle!!,currentDescription!!,taskCompleted,taskId!!))
        }
    }

    private fun updateTask(task: Task) = viewModelScope.launch {
        appRepository.saveTask(task)
        _taskUpdate.value = true
    }

    private fun createTask(task: Task)  = viewModelScope.launch {
        appRepository.saveTask(task)
        _taskUpdate.value = true
    }
}