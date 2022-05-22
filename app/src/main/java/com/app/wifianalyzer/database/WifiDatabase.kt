package com.app.wifianalyzer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WifiScan::class], version = 1, exportSchema = false)
abstract class WifiDatabase : RoomDatabase() {

    abstract val wifiScanDao: WifiScanDao

    companion object {

        @Volatile
        private var INSTANCE: WifiDatabase? = null

        fun getInstance(context: Context): WifiDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WifiDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}