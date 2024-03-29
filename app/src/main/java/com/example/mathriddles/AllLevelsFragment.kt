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
import androidx.recyclerview.widget.RecyclerView

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
        val recycler = view.findViewById(R.id.recyclerViewAllLevel) as RecyclerView
        viewModel.getAllLevels().observe(viewLifecycleOwner, Observer { returnedLevel ->
            val adapter = BAdapter(returnedLevel, onClick = {it -> view.findNavController().navigate(AllLevelsFragmentDirections
                    .actionAllLevelsFragmentToLevelFragment(it))})
            recycler.adapter = adapter
        })

        return view
    }

}