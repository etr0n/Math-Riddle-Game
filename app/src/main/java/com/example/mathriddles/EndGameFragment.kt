package com.example.mathriddles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController


class EndGameFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_end_game, container, false)

        view.findViewById<Button>(R.id.backToStart_btn).setOnClickListener{
            val action = EndGameFragmentDirections.actionEndGameFragmentToStartFragment()
            view.findNavController().navigate(action)
        }

       return view
    }



}