package com.example.mathriddles

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Level::class,Statistics::class, SwitchState::class, SummaryImage::class), version = 2)
abstract class GameDataBase : RoomDatabase() {
    abstract fun Dao(): Dao

    companion object{
        @Volatile
        private var _instance: GameDataBase? = null
        fun getInstance(context: Context): GameDataBase{
            return _instance ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDataBase::class.java,
                    "game-db"
                ).build()
                _instance = instance
                instance
            }
        }
    }
}