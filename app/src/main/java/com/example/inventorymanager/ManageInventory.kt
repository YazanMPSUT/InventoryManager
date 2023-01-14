package com.example.inventorymanager

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ManageInventory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_inventory)
        val btnDF : Button = findViewById(R.id.btnShowDelFrag)

        btnDF.setOnClickListener {
            val deleteFragment = DeleteDialogFragment()
            deleteFragment.show(supportFragmentManager,"Delete Dialog")
        }
    }

}