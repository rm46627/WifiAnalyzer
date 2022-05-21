package com.app.wifianalyzer.wifilist

import WifiScan
import android.app.Application
import android.net.wifi.ScanResult
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.wifianalyzer.database.WifiScanDao
import kotlinx.coroutines.launch

class WifiListViewModel(val database: WifiScanDao, application: Application) : AndroidViewModel(application) {

    val wifiList = MutableLiveData<List<ScanResult>>()

    val wifiListHistory = MutableLiveData<List<WifiScan>>()

    init {
        initializeHistory()
    }

    private fun initializeHistory() {
        viewModelScope.launch {
            wifiListHistory.value = getScansFromDatabase()
        }
    }

    private suspend fun getScansFromDatabase(): List<WifiScan> {
        return database.getAllScans()
    }

}