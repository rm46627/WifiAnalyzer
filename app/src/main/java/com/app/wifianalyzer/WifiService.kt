package com.app.wifianalyzer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.*
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.app.wifianalyzer.database.WifiDatabase
import com.app.wifianalyzer.database.WifiScan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep


class WifiService : LifecycleService() {

    val db = WifiDatabase.getInstance(this)
    private lateinit var wifiManager: WifiManager

    // About startScan()
    // "We are further limiting the number of scans apps can request to improve network performance
    // and improve battery life. The WifiManager.startScan() usage is limited to: - Each foreground
    // app is restricted to 4 scans every 2 minutes. - All background apps combined are restricted
    // to one scan every 30 minutes."

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        lifecycleScope.launch(Dispatchers.Default) {

            wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
            val intentFilter = IntentFilter()
            intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)

            // registerReceiver on new thread because db cannot be accessed on mainthread
            val handlerThread = HandlerThread("ht")
            handlerThread.start()
            val looper = handlerThread.looper
            val handler = Handler(looper)
            registerReceiver(wifiScanReceiver, intentFilter, null, handler)

            while(true){
                wifiManager.startScan()
                sleep(10000)
            }
        }

        return START_NOT_STICKY
    }

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

    private fun scanSuccess() {
        val list = wifiManager.scanResults
        for(sr :ScanResult in list){
            val scan = WifiScan()
            scan.ssid = sr.SSID
            scan.rssi = sr.level
            scan.freq = sr.frequency
            scan.linkSpeed = 0
            db.wifiScanDao.insert(scan)
        }
    }

    public fun getScans() : List<ScanResult>{
        return wifiManager.scanResults
    }

    // handling scan failure
    private fun scanFailure() {
        val scan = WifiScan()
        scan.ssid = "jakis Sfabrykowany"
        db.wifiScanDao.insert(scan)
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}