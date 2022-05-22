package com.app.wifianalyzer.wifilist


import android.app.Application
import android.net.wifi.ScanResult
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.wifianalyzer.database.WifiScanDao

class WifiListViewModel(val database: WifiScanDao, application: Application) : AndroidViewModel(application) {

    val wifiList = MutableLiveData<List<ScanResult>>()

    val wifiListHistory = database.getAllScans()

//    init {
//        initializeHistory()
//    }

//    private fun initializeHistory() {
//        wifiListHistory.value = database.getAllScans()
//    }

}