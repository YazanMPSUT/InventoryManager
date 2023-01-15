package com.example.inventorymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TableLayout
import android.widget.Toast

class ViewInventory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_inventory)
        val cols = listOf<String>("", "", "").toTypedArray()
        val LVIntDB: ListView = findViewById(R.id.inventoryLV)
        //TODO: All of this
        val crs = contentResolver.query(
            InventoryProvider.CONTENT_URI,
            arrayOf(
                InventoryProvider._id,
                InventoryProvider.name, InventoryProvider.buy_price,
                InventoryProvider.sell_price, InventoryProvider.supplier,
                InventoryProvider.quantity
            ),
            null,
            null,
            InventoryProvider._id
        )
       /* if (crs!!.moveToFirst()) {
            do {
                Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show()
            } while (crs.moveToNext())
        }*/

            val adapter = SimpleCursorAdapter(/* context = */ this,
                /* layout = */ android.R.layout.simple_expandable_list_item_2,
                /* c = */ crs,
                /* from = */ arrayOf(
                    InventoryProvider._id,
                    InventoryProvider.name, InventoryProvider.buy_price,
                    InventoryProvider.sell_price, InventoryProvider.supplier,
                    InventoryProvider.quantity
                ),
                /* to = */ intArrayOf(android.R.id.text1, android.R.id.text2),
                /* flags = */ 0
            )
            LVIntDB.adapter = adapter
            adapter.changeCursor(crs)

            /*  val from = listOf<String>("","").toTypedArray()
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
        )*/

        }
    }
