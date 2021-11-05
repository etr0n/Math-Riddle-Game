package com.example.mathriddles

import android.content.Context
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

    fun insertLevels(){
        viewModelScope.launch {
            database.Dao().insert(
                    listOf<Level>(
                            Level(
                                    1,
                                    "+3",
                                    12,
                                    R.drawable.lvl1,
                                    true,
                                    0,
                                ""
                            )  ,
                            Level(
                                    2,
                                    "x4",
                                    20,
                                    R.drawable.lvl2,
                                    false,
                                    0,
                                ""
                            ),
                            Level(
                                    3,
                                    "-",
                                    4,
                                    R.drawable.lvl3,
                                    false,
                                    0,
                                ""
                            ),
                            Level(
                                    4,
                                    "ABC",
                                    301,
                                    R.drawable.lvl4,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                            5,
                            "21",
                            21,
                            R.drawable.lvl5,
                            false,
                            0,
                            ""
                            )
                    )
            )
        }
    }

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
            val returnedLevels = database.Dao().getAllLevels()
            result.postValue(returnedLevels)
        }
        return result
    }
}