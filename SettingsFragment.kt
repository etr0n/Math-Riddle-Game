package com.example.mathriddles

import android.media.AudioManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val viewModel: LViewModel by viewModels{ViewModelFactory(requireContext())}


        view.findViewById<ImageButton>(R.id.settingsBack_btn).setOnClickListener{
            val action = SettingsFragmentDirections.actionSettingsFragmentToStartFragment()
            view.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.restart_btn).setOnClickListener{
            viewModel.deleteLevel()
            viewModel.deleteStatistic()
            Toast.makeText(activity, "restarted", Toast.LENGTH_LONG).show()
        }


        val audioManager = requireContext().getSystemService(AudioManager::class.java)
        val sw1 = view.findViewById<Switch>(R.id.switch_sound)
        sw1?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_UNMUTE,
                    0
                )
            } else {
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_MUTE,
                    0
                )
/*
                view.findViewById<Button>(R.id.switch_sound).layoutParams = ConstraintLayout(android:checked="true")
                val params = sw1.layoutParams as ConstraintLayout.LayoutParams
*/

                //sw1.setChecked(false)

            }
            val message = if (isChecked) "Sound:ON" else "Sound:OFF"
            Toast.makeText(
                context, message,
                Toast.LENGTH_SHORT
            ).show()
        }



        return view
    }
}