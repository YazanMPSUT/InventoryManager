package com.example.inventorymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inventorymanager.MainActivity.Companion
import com.example.inventorymanager.MainActivity.Companion.salesTotal

class AddInventory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory)
        salesTotal += 10
    }
}