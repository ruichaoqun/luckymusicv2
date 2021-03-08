package com.ruichaoqun.luckymusicv2.view.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ruichaoqun.luckymusicv2.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
    private val viewModel by viewModels<TasksViewModel>()
    private lateinit var viewDataBinding: FragmentTasksBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentTasksBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return viewDataBinding.root
    }

}