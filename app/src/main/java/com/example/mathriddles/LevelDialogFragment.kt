package com.example.mathriddles

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController


class LevelDialogFragment(var hint: String) : DialogFragment() {

    init {
        isCancelable = false
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        return AlertDialog.Builder(requireContext())
            .setView(R.layout.fragment_level_dialog)
            .setTitle("Need help?")
                .setMessage(hint)
            .setNeutralButton("Got it!") { dialog, _ ->
                dialog.dismiss()
            }.show()



    }

    companion object {
        const val TAG = "HintDialog"

    }


}