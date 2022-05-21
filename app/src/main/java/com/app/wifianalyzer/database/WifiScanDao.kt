package com.app.wifianalyzer.database

import WifiScan
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WifiScanDao {

    @Insert
    fun insert(scan: WifiScan)

    @Query("SELECT * FROM wifi_scan_results ORDER BY scan_time DESC")
    fun getAllScans(): List<WifiScan>
}