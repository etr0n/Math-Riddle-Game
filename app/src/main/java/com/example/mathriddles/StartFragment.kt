package com.example.mathriddles

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController



class StartFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        val viewModel: LViewModel by viewModels{ViewModelFactory(requireContext())}

        viewModel.getCount().observe(viewLifecycleOwner, Observer {
            if (it == 0){
            viewModel.insertLevels()
        }
        })

        view.findViewById<Button>(R.id.play_btn).setOnClickListener{
            viewModel.gameStartId().observe(viewLifecycleOwner, Observer {

                if(it != -1) {
                    val action = StartFragmentDirections.actionStartFragmentToLevelFragment(it)
                    view.findNavController().navigate(action)
                }
                else{
                    val action = StartFragmentDirections.actionStartFragmentToAllLevelsFragment()
                    view.findNavController().navigate(action)
                }
            })

        }

        view.findViewById<Button>(R.id.AllLevels_btn).setOnClickListener{
            val action = StartFragmentDirections.actionStartFragmentToAllLevelsFragment()
            view.findNavController().navigate(action)
        }
        view.findViewById<Button>(R.id.stat_btn).setOnClickListener{
            val action = StartFragmentDirections.actionStartFragmentToStatiscticsFragment()
            view.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.restart_btn).setOnClickListener{
            viewModel.insertLevels()
            viewModel.deleteStatistic()
            Toast.makeText(activity, "Game restarted", Toast.LENGTH_LONG).show()
        }
        return view
    }

}