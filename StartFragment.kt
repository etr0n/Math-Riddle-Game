package com.example.mathriddles

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
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
            viewModel.createLevelSequence()
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

        view.findViewById<Button>(R.id.settings_btn).setOnClickListener{
            val action = StartFragmentDirections.actionStartFragmentToSettingsFragment()
            view.findNavController().navigate(action)
        }

        viewModel.getCount().observe(viewLifecycleOwner, Observer {
            val prg = view.findViewById<ProgressBar>(R.id.progressBar)
            prg.max = it - 1

            viewModel.getProgress().observe(viewLifecycleOwner, Observer {
                val currentProgress = it - 1

                view.findViewById<TextView>(R.id.progress_textView).text =
                    "Done " + currentProgress + " of " + prg.max + " levels"

                ObjectAnimator.ofInt(prg, "Progress", currentProgress)
                    .setDuration(0)
                    .start()
            })
        })
/*
        view.findViewById<Button>(R.id.restart_btn).setOnClickListener{
            viewModel.deleteLevel()
            viewModel.deleteStatistic()
            Toast.makeText(activity, "Game restarted", Toast.LENGTH_LONG).show()
        }

*/

        return view
    }

}