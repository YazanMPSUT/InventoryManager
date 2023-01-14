package com.example.inventorymanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    companion object {
        var salesTotal: Double = 0.0
    }
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
    }
}
