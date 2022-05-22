package com.app.wifianalyzer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wifi_scan_results")
data class WifiScan(
    @PrimaryKey(autoGenerate = true)
    var scanId: Long = 0L,

    @ColumnInfo(name = "scan_time")
    val timeMili: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "ssid")
    var ssid: String = "",

    @ColumnInfo(name = "rssi")
    var rssi: Int = -1,

    @ColumnInfo(name = "link_speed")
    var linkSpeed: Int = -1,

    @ColumnInfo(name = "frequency")
    var freq: Int = -1)