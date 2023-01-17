    package com.example.inventorymanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*


    class MainActivity : AppCompatActivity() {
    companion object {
        var cashTotal: Double = 50000.0
    }
        var firstStart : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNewItemActivity : Button = findViewById(R.id.btnNewItemActivity)
        val btnManageActivity : Button = findViewById(R.id.btnManageActivity)
        val btnViewActivity : Button = findViewById(R.id.btnViewActivity)

        btnNewItemActivity.setOnClickListener {
            val intentNew = Intent(this,AddInventory::class.java)
            startActivity(intentNew)
        }

        btnManageActivity.setOnClickListener {
            val intentManage = Intent(this,ManageInventory::class.java)
            startActivity(intentManage)
        }
        btnViewActivity.setOnClickListener {
            val intentView = Intent(this,ViewInventory::class.java)
            startActivity(intentView)
        }
        if(firstStart){
            startService()
            firstStart = false
        }

    }

    private fun startService() {
        val serviceIntent = Intent(this, ReminderService::class.java)
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
        ContextCompat.startForegroundService(this, serviceIntent)

        val intent = Intent(this, ReminderService::class.java)
        val pintent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val alarmMgr = getSystemService(ALARM_SERVICE) as AlarmManager

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE,0)
        }

// With setInexactRepeating(), you have to use one of the AlarmManager interval
// constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr?.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pintent
        )
    }

    fun stopService() {
        val serviceIntent = Intent(this, ReminderService::class.java)
        stopService(serviceIntent)
    }

}
