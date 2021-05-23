package com.example.mathriddles

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class Level (
     @PrimaryKey(autoGenerate = true) val levelId: Int,
     val hint: String,
     val answer: Int,
     @DrawableRes val image: Int,
     val indicator : Boolean,
     val bestTime : Long,
     val date : String
)