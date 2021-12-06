package com.example.mathriddles

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import java.security.KeyStore

//funkcijos kuriomis galima naudotisa fragmentuose
//timeris
class LViewModel(context: Context): ViewModel(){

    val database = Room.databaseBuilder(context, GameDataBase::class.java, "levels").build()

    fun updateIndicator(id: Int, bestTime:Long, date:String){
        viewModelScope.launch {
            database.Dao().updateLevel( bestTime, date, id)
            database.Dao().updateLevelIndicator(true, id+1)
        }
    }

    fun getlevel(id: Int):LiveData<Level>{
        val result = MutableLiveData<Level>()
        viewModelScope.launch {
            val returnedLevel = database.Dao().getlevel(id)
            result.postValue(returnedLevel)
        }
        return result
    }

    fun gameStartId():LiveData<Int>{
        var result = MutableLiveData<Int>()

        viewModelScope.launch {
            try {
                val getId = database.Dao().getIdTrue(true)
                var i = database.Dao().getCount()

                if(i == getId){
                    result.postValue(-1)
                }
                else  result.postValue(getId)
            }
            catch (e: NullPointerException){

                result.postValue(-1)
            }
        }
        return result
    }
    fun gameSummaryId():LiveData<Int>{
        var result = MutableLiveData<Int>()

        viewModelScope.launch {
            try {
                val getCount = database.Dao().getCount()
                result.postValue(getCount)
            }
            catch (e: NullPointerException){

                result.postValue(-1)
            }
        }
        return result
    }

    fun getCount():LiveData<Int>{
        var result = MutableLiveData<Int>()


        viewModelScope.launch {
            try {
                val count = database.Dao().getCount()
                result.postValue(count)
            }
            catch (e: NullPointerException){

                result.postValue(-1)
            }

        }
        return result
    }
    fun getAllLevel():LiveData<List<Level>>{
        val result = MutableLiveData<List<Level>>()
        viewModelScope.launch {
            val returnedLevels = database.Dao().getAllLevel()
            result.postValue(returnedLevels)
        }
        return result
    }
    fun insertStatistic(time:Long, date: String, mistakes : Int, levelId:Int ){
        viewModelScope.launch {
            database.Dao().insertStatistics(
                    Statistics(
                        0,
                      levelId,
                        date,
                        time,
                        mistakes
                    )
            )
        }
    }
    fun getStatistic(id:Int):LiveData<List<Statistics>> {
        val result = MutableLiveData<List<Statistics>>()
        viewModelScope.launch {
           val returnedStatistics = database.Dao().getStatistics(id)
           result.postValue(returnedStatistics)
        }
        return result
    }
    fun deleteStatistic(){
        viewModelScope.launch {
            database.Dao().deleteStatistics()
        }
    }
    fun getAllLevels():LiveData<List<Level>>{

        val result = MutableLiveData<List<Level>>()
        viewModelScope.launch {
            val count = database.Dao().getCount()
            val returnedLevels = database.Dao().getAllLevels(count-1)
            result.postValue(returnedLevels)
        }
        return result
    }

    fun createLevelSequence() {
        for (i in 1..21 step 3){
            val rnd = (i+3..i+100).random()
            val numbersequence = generateSequence(rnd) { it + 3 }
            val numseqList= numbersequence.take(4).toList()
            if(i == 1) {
                viewModelScope.launch {
                    database.Dao().insert(
                            Level(
                                    i,
                                    "+",
                                    numseqList[3],
                                    numseqList.joinToString(separator = ", ", limit = 3, truncated = " ?"),
                                    true,
                                    0,
                                    ""
                            )
                    )
                }
            }
            else
            {
                viewModelScope.launch {
                    database.Dao().insert(
                            Level(
                                    i,
                                    "+",
                                    numseqList[3],
                                    numseqList.joinToString(separator = ", ", limit = 3, truncated = " ?"),
                                    false,
                                    0,
                                    ""
                            )
                    )
                }
            }

        }
        for(i in 2..21 step 3 ){
            val rnd = (10..20).random()
            val rnd1 =(1..9).random()
            val ans = rnd*rnd1
            viewModelScope.launch {
                database.Dao().insert(
                        Level(
                                i,
                                "*",
                                ans,
                                "$rnd,$rnd1",
                                false,
                                0,
                                ""
                        )
                )
            }

        }
        for(i in 3..21 step 3 ){
            val rnd = (i+1..30).random()
            val numbersequence = generateSequence(rnd) { it * 2 }
            val numseqList= numbersequence.take(4).toList()

            viewModelScope.launch {
                database.Dao().insert(
                    Level(
                        i,
                        "+",
                        numseqList[3],
                        numseqList.joinToString(separator = ", ", limit = 3, truncated = " ?"),
                        false,
                        0,
                        ""
                    )
                )
            }
        }
    }

    fun deleteLevel(){
        viewModelScope.launch {
            database.Dao().deleteLevels()
        }
    }

    fun getProgress():LiveData<Int>{
        var result = MutableLiveData<Int>()


        viewModelScope.launch {
            try {
                val count = database.Dao().getProgress(true)
                result.postValue(count)
            }
            catch (e: NullPointerException){

                result.postValue(-1)
            }

        }
        return result
    }

    fun insertSwitch(state: Boolean){
        viewModelScope.launch {
            val i = database.Dao().getSwitchCount()
            if (i <= 0) {
                database.Dao().insertSwitch(
                    SwitchState(
                        0,
                        state
                    )
                )
            }
        }
    }

    fun updateSwitch(state: Boolean)
    {
        viewModelScope.launch {
            database.Dao().updateSwitch(state)
        }
    }

    fun getSwitch():LiveData<Boolean>{
        var result = MutableLiveData<Boolean>()
        viewModelScope.launch {
            val state = database.Dao().getSwitch()
            result.postValue(state)

        }

        return result
    }

    fun insertImage(){
        viewModelScope.launch {
            if (database.Dao().getImgCount() == 0){
                database.Dao().insertImage(SummaryImage(0,
                    "perfect",
                    "https://media.giphy.com/media/qMi9FmYD8zXH15Fcw3/giphy.gif",
                    10))
                database.Dao().insertImage(SummaryImage(0,
                    "good job",
                    "https://media.giphy.com/media/3o7abKhOpu0NwenH3O/giphy.gif",
                    8))
                database.Dao().insertImage(SummaryImage(0,
                    "it's good, but it's not enough",
                    "https://media.giphy.com/media/LluZfzq5cmmFdZN3pi/giphy.gif",
                    6))
                database.Dao().insertImage(SummaryImage(0,
                    "not good",
                    "https://media.giphy.com/media/A3t48v7vgzk1G/giphy.gif",
                    4))
                database.Dao().insertImage(SummaryImage(0,
                    "it's bad",
                    "https://media.giphy.com/media/xT1R9YUaUwR49MdDLa/giphy.gif",
                    2))
                database.Dao().insertImage(SummaryImage(0,
                    "it's horrible",
                    "https://media.giphy.com/media/oHwvYifyqlwGKNHeS4/giphy.gif",
                    0))
            }
        }
    }

    fun getUrl(score: Int):LiveData<String>{
        var result = MutableLiveData<String>()
        viewModelScope.launch {
            if (database.Dao().getImgUrl(score) != null){
                result.postValue(database.Dao().getImgUrl(score))
            }
            else{
                result.postValue(database.Dao().getImgUrl(score - 1))
            }
        }

        return result
    }
}