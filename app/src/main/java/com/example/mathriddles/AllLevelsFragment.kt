package com.example.mathriddles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

 class AllLevelsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_levels, container, false)



        view.findViewById<ImageButton>(R.id.LevelBack_btn).setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_allLevelsFragment_to_startFragment)
        }


        val viewModel: LViewModel by viewModels{ViewModelFactory(requireContext())}

        viewModel.getlevel(1).observe(viewLifecycleOwner, { returnedLevel ->
          if(returnedLevel.indicator){
              view.findViewById<Button>(R.id.lvl1_btn).isEnabled = true
          }})
            view.findViewById<Button>(R.id.lvl1_btn).setOnClickListener{
                val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(1)
                view.findNavController().navigate(action)
            }
        viewModel.getlevel(2).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl2_btn).isEnabled = true
            }})
            view.findViewById<Button>(R.id.lvl2_btn).setOnClickListener{
                val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(2)
                view.findNavController().navigate(action)
            }
        viewModel.getlevel(3).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl3_btn).isEnabled = true
            }})
            view.findViewById<Button>(R.id.lvl3_btn).setOnClickListener{
                val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(3)
                view.findNavController().navigate(action)
            }
        viewModel.getlevel(4).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl4_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl4_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(4)
            view.findNavController().navigate(action)
        }


        return view
    }

}