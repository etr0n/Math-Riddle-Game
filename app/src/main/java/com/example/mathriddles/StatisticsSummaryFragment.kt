package com.example.mathriddles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


class StatisticsSummaryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_statistics_summary, container, false)
        val args = StatisticsSummaryFragmentArgs.fromBundle(requireArguments())

        val viewModel: LViewModel by viewModels { ViewModelFactory(requireContext()) }

        view.findViewById<ImageButton>(R.id.statiscticssummaryBack_btn).setOnClickListener{
            val action = StatisticsSummaryFragmentDirections.actionStatisticsSummaryFragmentToStatiscticsFragment()
            view.findNavController().navigate(action)
        }
        val recycler = view.findViewById(R.id.recyclerViewStatSummary) as RecyclerView

        viewModel.getStatistic(args.id).observe(viewLifecycleOwner, Observer {returnedStatistics ->

            recycler.adapter = SAdapter(returnedStatistics)
            //recycler.adapter?.notifyDataSetChanged()
        })


        return view
    }


}