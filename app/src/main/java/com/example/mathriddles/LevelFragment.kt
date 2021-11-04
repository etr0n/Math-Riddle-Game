package com.example.mathriddles

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.text.format.DateFormat.format
import android.util.Log
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
import com.google.android.gms.ads.*
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import java.lang.String.format
import java.text.DateFormat
import java.text.DateFormat.MINUTE_FIELD
import java.text.Format
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*


class LevelFragment() : Fragment() , LevelDialogFragment {

    val AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917"
    val TAG = "MainActivity"
    private var mRewardedAd: RewardedAd? = null
    private var mIsLoading = false

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_level, container, false)
        val args = LevelFragmentArgs.fromBundle(requireArguments())
        MobileAds.initialize(requireContext()) {}





        val viewModel: LViewModel by viewModels{ViewModelFactory(requireContext())}

        viewModel.getlevel(args.Id).observe(viewLifecycleOwner, Observer { returnedLevel ->
            val image: ImageView = view.findViewById(R.id.riddle_imageView)
            image.setImageResource(returnedLevel.image)


            view.findViewById<ImageButton>(R.id.hint_btn).setOnClickListener {
                loadRewardedAd()
             /* LevelDialogFragment(returnedLevel.hint).show(childFragmentManager, LevelDialogFragment.TAG)*/
                onCreateDialog()
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

    private fun loadRewardedAd() {
        if (mRewardedAd == null) {
            mIsLoading = true
            var adRequest = AdRequest.Builder().build()

            RewardedAd.load(
                    this.requireContext(), AD_UNIT_ID, adRequest,
                    object : RewardedAdLoadCallback() {
                        override fun onAdFailedToLoad(adError: LoadAdError) {
                            Log.d(TAG, adError?.message)
                            mIsLoading = false
                            mRewardedAd = null
                        }

                        override fun onAdLoaded(rewardedAd: RewardedAd) {
                            Log.d(TAG, "Ad was loaded.")
                            mRewardedAd = rewardedAd
                            mIsLoading = false
                        }
                    }
            )
        }
    }
    override fun onCreateDialog() {


        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_level_dialog, null)
        builder.setView(view)
                .setTitle("Need help?")
                .setNeutralButton("Got it") {dialog, _ -> dialog.dismiss()}

        val btnSolution = view.findViewById<Button>(R.id.button_solution_ad)
        val btnHint = view.findViewById<Button>(R.id.button_hint_ad)

        btnSolution?.setOnClickListener{
            showRewardedVideo()
            Toast.makeText(context, "clicked SOLUTION", Toast.LENGTH_LONG).show()
            btnSolution.visibility = View.INVISIBLE
            btnHint.visibility = View.INVISIBLE
        }
        btnHint?.setOnClickListener{
            showRewardedVideo()
            Toast.makeText(context, "clicked HINT", Toast.LENGTH_LONG).show()
            btnHint.visibility = View.INVISIBLE
            btnSolution.visibility = View.INVISIBLE
        }
         builder.show()
    }

    private fun showRewardedVideo() {
       /* show_video_button.visibility = View.INVISIBLE*/
        if (mRewardedAd != null) {
            mRewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mRewardedAd = null
                    loadRewardedAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d(TAG, "Ad failed to show.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mRewardedAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                    // Called when ad is dismissed.
                }
            }

            mRewardedAd?.show(
                    this.requireActivity(),
                    OnUserEarnedRewardListener() {
                        fun onUserEarnedReward(rewardItem: RewardItem) {
                            var rewardAmount = rewardItem.amount
                           /* addCoins(rewardAmount)*/
                            Log.d("TAG", "User earned the reward.")
                        }
                    }
            )
        }
    }

}


