package com.example.mathriddles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.lifecycle.Observer
import java.text.SimpleDateFormat
import java.util.*


class SummaryFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        val args = SummaryFragmentArgs.fromBundle(requireArguments())

        view.findViewById<TextView>(R.id.timer_textView).text = convertLongToTime(args.timer)

        view.findViewById<Button>(R.id.nextlvl_btn).setOnClickListener{

            val viewModel: LViewModel by viewModels{ViewModelFactory(requireContext())}

            viewModel.gameStartId().observe(viewLifecycleOwner, Observer {

                if (it != -1){
                    val action = SummaryFragmentDirections.actionSummaryFragmentToLevelFragment(it)
                    view.findNavController().navigate(action)
                }
                else {
                    val action = SummaryFragmentDirections.actionSummaryFragmentToEndGameFragment()
                    view.findNavController().navigate(action)
                }


            })


        }
        return view
    }
    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("mm:ss")
        return format.format(date)
    }

}