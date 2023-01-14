package com.example.inventorymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class ViewInventory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_inventory)
        val cols = listOf<String>("","","").toTypedArray()
        val LVIntDB : ListView = findViewById(R.id.inventoryLV)
        //TODO: All of this
        val from = listOf<String>("","").toTypedArray()
        val to = intArrayOf(android.R.id.text1,android.R.id.text2)
        val rs = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI ,
        cols,
            null,
            null,
            cols[0]    )

        val adapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2,
            rs,
            from,
            to,
            0
        )
        //TODO: All of this

        LVIntDB.adapter = adapter

    }
}