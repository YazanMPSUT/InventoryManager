    package com.example.inventorymanager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.lang.Double
import java.text.DecimalFormat


    class MainActivity : AppCompatActivity() {

        private var firstStart: Boolean = true

        companion object{
            var CurrentBalance : kotlin.Double = 0.0;
        }
        @SuppressLint("SetTextI18n", "CommitPrefEdits", "UseValueOf")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val sharedPreference =  getSharedPreferences("PREFS", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            if(getSharedPreferences("PREFS", MODE_PRIVATE)
                    .getString("CurrentBalance",
                        Double(50000.0).toString()) == "50000.0" ) {
                editor.putString("CurrentBalance", Double(50000.00).toString()).apply()
            }

            CurrentBalance = getSharedPreferences("PREFS", MODE_PRIVATE)
                .getString("CurrentBalance",
                    Double(50000.0).toString())?.toDouble()!!

            val btnNewItemActivity: Button = findViewById(R.id.btnNewItemActivity)
            val btnManageActivity: Button = findViewById(R.id.btnManageActivity)
            val btnViewActivity: Button = findViewById(R.id.btnViewActivity)
            val btnRefreshBalance : Button = findViewById(R.id.btnRefreshBalance)
            val tvMoney : TextView = findViewById(R.id.tvMoney)


            btnNewItemActivity.setOnClickListener {
                val intentNew = Intent(this, AddInventory::class.java)
                startActivity(intentNew)
            }

            btnManageActivity.setOnClickListener {
                val intentManage = Intent(this, ManageInventory::class.java)
                startActivity(intentManage)
            }

            btnViewActivity.setOnClickListener {
                val intentView = Intent(this, ViewInventory::class.java)
                stopService()
                startActivity(intentView)
            }
            tvMoney.text = "$${DecimalFormat("#.00",).format(CurrentBalance)}"

            btnRefreshBalance.setOnClickListener {
                tvMoney.text = "$${DecimalFormat("#.00",).format(CurrentBalance)}"

            }

            if(CurrentBalance < 0.0) {
                tvMoney.setTextColor(Color.parseColor("#c30010"))
            }

            if (firstStart) {
                startService()
                firstStart = false
            }


        }

        override fun onDestroy() {
            stopService()
            getSharedPreferences("PREFS", MODE_PRIVATE).edit().putString("CurrentBalance",
                CurrentBalance.toString()).apply()
            super.onDestroy()
        }

        private fun startService() {
            val serviceIntent = Intent(this, ReminderService::class.java)
            serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
            ContextCompat.startForegroundService(this, serviceIntent)
        }

        private fun stopService() {
            val serviceIntent = Intent(this, ReminderService::class.java)
            stopService(serviceIntent)
        }


    }
