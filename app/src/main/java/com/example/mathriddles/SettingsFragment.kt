package com.example.mathriddles

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController


class SettingsFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val viewModel: LViewModel by viewModels{ViewModelFactory(requireContext())}

        view.findViewById<Button>(R.id.button_switch_theme).setOnClickListener {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        view.findViewById<ImageButton>(R.id.settingsBack_btn).setOnClickListener{
            val action = SettingsFragmentDirections.actionSettingsFragmentToStartFragment()
            view.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.restart_btn).setOnClickListener{
            viewModel.deleteLevel()
            viewModel.deleteStatistic()
            viewModel.createLevelSequence()
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