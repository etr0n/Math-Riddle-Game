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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.rewarded.RewardedAd


interface LevelDialogFragment {

    /*   val builder = AlertDialog.Builder(requireContext())
       val view = LayoutInflater.from(context).inflate(R.layout.fragment_level_dialog, null)
       builder.setView(view)
           .setTitle("Need help?")
           .setNeutralButton("Got it") {dialog, _ -> dialog.dismiss()}

       val btnSolution = view.findViewById<Button>(R.id.button_solution_ad)
       val btnHint = view.findViewById<Button>(R.id.button_hint_ad)

       btnSolution?.setOnClickListener{
           Toast.makeText(context, "clicked SOLUTION", Toast.LENGTH_LONG).show()
           btnSolution.visibility = View.INVISIBLE
           btnHint.visibility = View.INVISIBLE
       }
       btnHint?.setOnClickListener{
           Toast.makeText(context, "clicked HINT", Toast.LENGTH_LONG).show()
           btnHint.visibility = View.INVISIBLE
           btnSolution.visibility = View.INVISIBLE
       }
       return builder.create()

   }*/




}