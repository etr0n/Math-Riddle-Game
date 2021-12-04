package com.example.mathriddles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController


class EditProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewModel: LViewModel by viewModels { ViewModelFactory(requireContext()) }
        val view = inflater.inflate(R.layout.fragment_level, container, false)

        view.findViewById<ImageButton>(R.id.LevelBack_btn).setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_editProfile_to_settingsFragment)
        }
        return view
    }

}