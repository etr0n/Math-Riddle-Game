package com.example.mathriddles

import androidx.annotation.DrawableRes
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
 data class Level (
     @PrimaryKey(autoGenerate = true) val levelId: Int,
     val hint: String,
     val answer: Int,
     val sequence: String,
     val indicator : Boolean,
     val bestTime : Long,
     val date : String
)

@Entity
data class Statistics(
    @PrimaryKey(autoGenerate = true) val statisticsId: Int,
    val levelTimeId : Int,
    val date : String,
    val time : Long,
    val mistakes : Int
)

data class LevelWithStatistics(
    @Embedded val level : Level,
    @Relation(
        parentColumn = "levelId",
        entityColumn = "levelTimeId"
    )
    val statistics: List<Statistics>
)

@Entity
data class SwitchState(
    @PrimaryKey(autoGenerate = true) val switchId: Int,
    val switchState : Boolean
)

@Entity
data class SummaryImage(
    @PrimaryKey(autoGenerate = true) val imageId: Int,
    val imageName : String,
    val imageUrl : String,
    val imageScore : Int
)
