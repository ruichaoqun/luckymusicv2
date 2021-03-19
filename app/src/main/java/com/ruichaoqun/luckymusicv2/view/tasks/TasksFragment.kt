package com.ruichaoqun.luckymusicv2.view.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ruichaoqun.luckymusicv2.databinding.FragmentTasksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment() {
    private val viewModel by viewModels<TasksViewModel>()
    private lateinit var viewDataBinding: FragmentTasksBinding
    private lateinit var listAdapter:TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentTasksBinding.inflate(inflater,container,false).apply {
            vm = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        listAdapter = TaskAdapter()
        viewDataBinding.recyclerView.adapter = listAdapter
        viewModel.items.observe(viewLifecycleOwner){
            listAdapter.setNewItems(it)
        }
        viewDataBinding.fabAddTask.setOnClickListener {
            findNavController().navigate(TasksFragmentDirections.actionTasksFragmentDestToAddTaskFragment(null,"Add Task"))
        }
    }
}