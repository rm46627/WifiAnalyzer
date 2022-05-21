package com.app.wifianalyzer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.*
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WifiService : LifecycleService() {

    private val wifiManager = this.getSystemService(Context.WIFI_SERVICE) as WifiManager

    private val wifiScanReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                scanSuccess()
            } else {
                scanFailure()
            }
        }
    }

    // About startScan()
    // "We are further limiting the number of scans apps can request to improve network performance
    // and improve battery life. The WifiManager.startScan() usage is limited to: - Each foreground
    // app is restricted to 4 scans every 2 minutes. - All background apps combined are restricted
    // to one scan every 30 minutes."

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

            lifecycleScope.launch(Dispatchers.Default) {

            // val results: List<ScanResult> = wifiManager.getScanResults()
            val intentFilter = IntentFilter()
            intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
            registerReceiver(wifiScanReceiver, intentFilter)

            val success = wifiManager.startScan()
            if (!success) {
                // scan failure handling
                scanFailure()
            }
        }

        return START_NOT_STICKY
    }

    private fun scanSuccess() {
        //wifiList.value = wifiManager.scanResults
    }

    // handling scan failure
    private fun scanFailure() {
        // empty list
        //wifiList.value = listOf()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}