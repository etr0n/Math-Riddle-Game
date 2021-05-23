package com.example.mathriddles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import org.w3c.dom.Text


class StatiscticsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_statisctics, container, false)
        val viewModel: LViewModel by viewModels { ViewModelFactory(requireContext()) }

        view.findViewById<ImageButton>(R.id.statiscticsBack_btn).setOnClickListener{
            val action = StatiscticsFragmentDirections.actionStatiscticsFragmentToStartFragment()
            view.findNavController().navigate(action)
        }
        val recycler = view.findViewById(R.id.recyclerView) as RecyclerView



        viewModel.getAllLevel().observe(viewLifecycleOwner, Observer { returnedLevel ->

            if(returnedLevel.isNotEmpty())
            {
                view.findViewById<TextView>(R.id.lvl_textView).text = "LVL"
                view.findViewById<TextView>(R.id.datetimestats_textView).text = "Date & time"
                view.findViewById<TextView>(R.id.bestscore_textView).text = "Best score"
                recycler.adapter = LAdapter(returnedLevel)

            }
            else view.findViewById<TextView>(R.id.noData_textView).text = "No data found"

        })
        recycler.adapter?.notifyDataSetChanged()

        return view
    }



}