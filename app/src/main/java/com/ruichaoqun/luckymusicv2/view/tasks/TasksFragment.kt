package com.ruichaoqun.luckymusicv2.view.tasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ruichaoqun.luckymusicv2.R
import com.ruichaoqun.luckymusicv2.databinding.FragmentTasksBinding
import com.ruichaoqun.luckymusicv2.utils.initSchemeColors
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
        listAdapter = TaskAdapter(viewModel)
        viewDataBinding.recyclerView.adapter = listAdapter


        viewModel.items.observe(viewLifecycleOwner){
            listAdapter.submitList(it)
        }
        viewDataBinding.refreshLayout.apply {
            setOnRefreshListener {
                viewModel.refresh()
            }
            initSchemeColors()
        }
        viewModel.newTaskEvent.observe(viewLifecycleOwner){
            Log.w("AAAAA","newTaskEvent")
            findNavController().navigate(TasksFragmentDirections.actionTasksFragmentDestToAddTaskFragment(null,"Add Task"))
        }
        viewModel.toast.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewDataBinding.fabAddTask.setOnClickListener {
            findNavController().navigate(TasksFragmentDirections.actionTasksFragmentDestToAddTaskFragment(null,"Add Task"))
        }
//        activity?.findViewById<FloatingActionButton>(R.id.fab_add_task)?.let {
//            it.setOnClickListener {
//            }
//        }
    }
}