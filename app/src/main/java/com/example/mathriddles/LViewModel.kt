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

  /*  init {
        insertLevels()
    }*/

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
                                    "Numbers increase in a clockwise direction",
                                    3,
                                    R.drawable.lvl5,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    6,
                                    "x2",
                                    32,
                                    R.drawable.lvl6,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    7,
                                    "+",
                                    85,
                                    R.drawable.lvl7,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    8,
                                    "-",
                                    10,
                                    R.drawable.lvl8,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    9,
                                    "x3-4",
                                    29,
                                    R.drawable.lvl9,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    10,
                                    "Numbers increase in a clockwise direction",
                                    15,
                                    R.drawable.lvl10,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    11,
                                    "x2+1",
                                    63,
                                    R.drawable.lvl11,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    12,
                                    "-",
                                    5,
                                    R.drawable.lvl12,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    13,
                                    "+",
                                    55,
                                    R.drawable.lvl13,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    14,
                                    "x2+2",
                                    86,
                                    R.drawable.lvl14,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    15,
                                    "Numbers increase in a clockwise direction",
                                    9,
                                    R.drawable.lvl15,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    16,
                                    "ABCD",
                                    2150,
                                    R.drawable.lvl16,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    17,
                                    "+1 & multiplication",
                                    80,
                                    R.drawable.lvl17,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    18,
                                    "+",
                                    65,
                                    R.drawable.lvl18,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    19,
                                    "x6+6",
                                    1122,
                                    R.drawable.lvl19,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                                    20,
                                    "Numbers increase in a clockwise direction",
                                    16,
                                    R.drawable.lvl20,
                                    false,
                                    0,
                                    ""
                            ),
                            Level(
                            21,
                            "21",
                            21,
                            R.drawable.lvl2,
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
                val indicator = database.Dao().getIdTrue(true)
                var i = database.Dao().getCount()

                if(i == indicator){
                    result.postValue(-1)
                }
                else  result.postValue(indicator)

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

}