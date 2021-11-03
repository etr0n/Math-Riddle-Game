package com.example.mathriddles

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import java.lang.String.format
import java.text.DateFormat
import java.text.DateFormat.MINUTE_FIELD
import java.text.Format
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*


class LevelFragment : Fragment()  {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

       val view = inflater.inflate(R.layout.fragment_level, container, false)
        val args = LevelFragmentArgs.fromBundle(requireArguments())


        val viewModel: LViewModel by viewModels{ViewModelFactory(requireContext())}

        viewModel.getlevel(args.Id).observe(viewLifecycleOwner, Observer { returnedLevel ->
            val image: ImageView = view.findViewById(R.id.riddle_imageView)
            image.setImageResource(returnedLevel.image)


            view.findViewById<ImageButton>(R.id.hint_btn).setOnClickListener {
                LevelDialogFragment(returnedLevel.hint).show(childFragmentManager, LevelDialogFragment.TAG)

            }
            view.findViewById<TextView>(R.id.number_textView).text = args.Id.toString()


            val meter = view.findViewById<Chronometer>(R.id.c_meter)
            meter.start()
            var balas = 10;
            var klaidu_kiekis = 0;
            view.findViewById<Button>(R.id.submit_btn).setOnClickListener {
                try {
                    val answer: Int = view.findViewById<EditText>(R.id.answer_textField).text.toString().toInt()

                    if (answer == returnedLevel.answer) {
                        meter.stop()
                        val m  = SystemClock.elapsedRealtime() - meter.base;
                        ///
                        val date = getCurrentDateTime()
                        val dateInString = date.toString("yyyy/MM/dd    HH:mm")
                        var best:Long
                        if(returnedLevel.bestTime <= m && returnedLevel.bestTime.toInt() != 0)
                        {
                            best = returnedLevel.bestTime
                        }
                        else best = m


                        val action = LevelFragmentDirections.actionLevelFragmentToSummaryFragment(m,balas)
                        view.findNavController().navigate(action)

                        viewModel.updateIndicator(args.Id, best, dateInString)
                        viewModel.insertStatistic(m,dateInString,klaidu_kiekis,returnedLevel.levelId)


                    } else {
                        view.findViewById<TextView>(R.id.Error_textView).text = "Wrong. Try Again!"
                        klaidu_kiekis+=1
                        view.findViewById<TextView>(R.id.textView4_kiekis).text = "Errors made "+klaidu_kiekis.toString()
                        if(balas > 0)
                        {
                            balas=balas-1

                        }







                    }


                }
                catch (e: NumberFormatException ){
                   val answer = 0
                    view.findViewById<TextView>(R.id.Error_textView).text = "Wrong. Try Again!"
                }


            }


        })


        view.findViewById<ImageButton>(R.id.LevelBack_btn).setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_levelFragment_to_startFragment)
        }




        return view
    }


    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }


}