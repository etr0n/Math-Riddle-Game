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

        viewModel.getlevel(1).observe(viewLifecycleOwner, Observer { returnedLevel ->
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
        viewModel.getlevel(5).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl5_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl5_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(5)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(6).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl6_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl6_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(6)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(7).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl7_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl7_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(7)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(8).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl8_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl8_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(8)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(9).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl9_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl9_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(9)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(10).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl10_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl10_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(10)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(11).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl11_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl11_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(11)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(12).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl12_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl12_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(12)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(13).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl13_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl13_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(13)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(14).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl14_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl14_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(14)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(15).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl15_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl15_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(15)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(16).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl16_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl16_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(16)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(17).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl17_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl17_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(17)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(18).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl18_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl18_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(18)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(19).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl19_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl19_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(19)
            view.findNavController().navigate(action)
        }
        viewModel.getlevel(20).observe(viewLifecycleOwner, Observer { returnedLevel ->
            if(returnedLevel.indicator){
                view.findViewById<Button>(R.id.lvl20_btn).isEnabled = true
            }})
        view.findViewById<Button>(R.id.lvl20_btn).setOnClickListener{
            val action = AllLevelsFragmentDirections.actionAllLevelsFragmentToLevelFragment(20)
            view.findNavController().navigate(action)
        }

        return view
    }

}