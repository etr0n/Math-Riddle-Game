package com.example.mathriddles

import androidx.room.*
import androidx.room.Dao
import java.util.concurrent.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert( level: Level)

    @Query ("SELECT * FROM Level WHERE levelId = :id")
    suspend fun getlevel (id: Int): Level

    @Query("DELETE FROM Level")
    suspend fun deleteLevels()

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

    @Query("SELECT * FROM statistics WHERE levelTimeId = :id ")
    suspend fun getStatistics(id: Int): List<Statistics>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatistics( statistics: Statistics)

    @Query("DELETE FROM Statistics")
    suspend fun deleteStatistics()

    @Query("SELECT * FROM Level LIMIT :size")
    //@Query("SELECT *, COUNT(*) AS size FROM level LIMIT size ")
    suspend fun getAllLevels(size:Int):List<Level>

    @Query ("SELECT COUNT(*) From Level WHERE indicator = :indicator")
    suspend fun getProgress(indicator: Boolean): Int

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSwitch( switchState: SwitchState)

    @Query("SELECT COUNT(*) From SwitchState ")
    suspend fun getSwitchCount(): Int

    @Query("UPDATE SwitchState SET switchState=:state")
    suspend fun updateSwitch(state:Boolean)

    @Query ("SELECT switchState From SwitchState ORDER BY switchId DESC LIMIT 1")
    suspend fun getSwitch(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage( summaryImage: SummaryImage)

    @Query ("SELECT * FROM SummaryImage WHERE imageId = :id")
    suspend fun getImage (id: Int): SummaryImage

    @Query("DELETE FROM SummaryImage")
    suspend fun deleteImages()

    @Query("SELECT COUNT(*) From SummaryImage ")
    suspend fun getImgCount(): Int

    @Query ("SELECT imageId From SummaryImage WHERE imageName = :imageName LIMIT 1")
    suspend fun getImgId(imageName: String): Int

    @Query ("SELECT imageUrl From SummaryImage WHERE imageScore = :imageScore LIMIT 1")
    suspend fun getImgUrl(imageScore: Int): String
}