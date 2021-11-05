package com.example.mathriddles

import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ColorFormatter
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat


class StatisticsSummaryFragment : Fragment() {

    lateinit var lineChart: LineChart
    lateinit var statisticsList:ArrayList<Statistics>
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

            lineChart = view.findViewById(R.id.lineChart)
            initLineChart()
            setDataToLineChart(returnedStatistics)
        })

        return view
    }

    fun initLineChart(){
        // hide grid lines
        lineChart.axisLeft.setDrawGridLines(false)
        val xAxis:XAxis = lineChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

      //  lineChart.axisLeft.valueFormatter = MyAxisFormatter()
        lineChart.axisLeft.axisMinimum = -1.35F

        //remove right y-axis
        lineChart.axisRight.isEnabled = false
        lineChart.setPinchZoom(true)

        //remove legend
        lineChart.legend.isEnabled = false

        //remove description label
        lineChart.description.isEnabled = false

        // to draw label on xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f

    }

    private fun setDataToLineChart(timeList:List<Statistics>) {
        val entries: ArrayList<Entry> = ArrayList()
        timeList.toTypedArray()

        statisticsList = timeList as ArrayList<Statistics>
        var a=0
        for (i in statisticsList.indices) {
            a++
            val time = statisticsList[i]
            entries.add(Entry(a.toFloat(), time.mistakes.toFloat()))
        }

        val lineDataSet = LineDataSet(entries, "")
        lineDataSet.valueTextSize=15f
        lineDataSet.valueFormatter = DefaultValueFormatter(0)
        lineDataSet.color = R.color.purple_500
        lineDataSet.setCircleColor(R.color.purple_500)
        lineDataSet.lineWidth = 2f

        val data = LineData(lineDataSet)

        lineChart.data = data
        lineChart.invalidate()
    }
}