package com.example.mathriddles

import androidx.room.*
import androidx.room.Dao
import java.util.concurrent.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert( level: List<Level>)

    @Query ("SELECT * FROM Level WHERE levelId = :id")
    suspend fun getlevel (id: Int): Level

    @Query ("SELECT image From Level WHERE levelId= :id")
    suspend fun getImage(id: Int): Int

    @Query("SELECT COUNT(*) From Level ")
    suspend fun getCount(): Int

    @Query ("SELECT levelId From Level WHERE indicator = :indicator LIMIT 1")
    suspend fun getId(indicator: Boolean): Int

    @Query("UPDATE Level SET  bestTime=:bestTime, date=:date WHERE levelId= :id")
    suspend fun updateLevel(bestTime:Long, date: String, id: Int)

    @Query("UPDATE Level SET indicator =:indicator WHERE levelId= :id")
    suspend fun updateLevelIndicator(indicator: Boolean, id: Int)

    @Query("SELECT * FROM Level WHERE bestTime != 0 ")
    suspend fun getAllLevel():List<Level>

    @Query ("SELECT levelId From Level WHERE indicator = :indicator ORDER BY levelId DESC LIMIT 1")
    suspend fun getIdTrue(indicator: Boolean): Int

  //////
    @Transaction
    @Query("SELECT * FROM Level")
    suspend fun getLevelsWithStatistics():List<LevelWithStatistics>

    @Query("SELECT * FROM statistic WHERE levelTimeId = :id ")
    suspend fun getStatistics(id: Int): List<Statistic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatistics( statistic: Statistic)

}