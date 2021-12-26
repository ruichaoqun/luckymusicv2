package com.ruichaoqun.luckymusicv2.view.addtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ruichaoqun.luckymusicv2.databinding.AddTaskFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : Fragment() {
    private val viewModel by viewModels<AddTaskViewModel>()
    private lateinit var binding:AddTaskFragmentBinding
    private val args:AddTaskFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddTaskFragmentBinding.inflate(inflater,container,false).apply {
            vm = viewModel
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getTaskDetail(args.taskId)
        viewModel.taskUpdate.observe(viewLifecycleOwner,{
            findNavController().navigate(AddTaskFragmentDirections.actionAddTaskFragmentToTasksFragmentDest())
        })
        viewModel.toastMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        }
    }

}