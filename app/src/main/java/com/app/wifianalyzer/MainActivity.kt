package com.app.wifianalyzer

import android.Manifest.permission.*
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.app.wifianalyzer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        checkForPermissions()

        startService(Intent(this, WifiService::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, WifiService::class.java))
    }

    ////
    // PERMISSIONS
    ////

    companion object {
        private const val ALL_PERM_CODE = 100
    }

    // list of permissions to request
    val permissions = arrayOf(
        CHANGE_WIFI_STATE,
        ANSWER_PHONE_CALLS,
        ACCESS_COARSE_LOCATION,
        ACCESS_WIFI_STATE )

    private fun checkForPermissions() {
        ActivityCompat.requestPermissions(this, permissions, ALL_PERM_CODE)
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == ALL_PERM_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Permissions Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Permissions are required for the application to work!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}