package com.example.inventorymanager

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.IllegalArgumentException

class AddInventory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory)
        val btnAdd : Button = findViewById(R.id.btnAdd)


        btnAdd.setOnClickListener {
            val pnameET : EditText = findViewById(R.id.ETpid)
            val pidET : EditText = findViewById(R.id.ETqty)
            val bpET : EditText = findViewById(R.id.bpET)
            val spET : EditText = findViewById(R.id.spET)
            val snET : EditText = findViewById(R.id.snET)
            val qtyET : EditText = findViewById(R.id.qtyET)

            val pid : String = pidET.text.toString()
            val pname : String = pnameET.text.toString()
            val bp : String = bpET.text.toString()
            val sp : String = spET.text.toString()
            val sn : String = snET.text.toString()
            val qty : String = qtyET.text.toString()

            val values = ContentValues()
            values.put(InventoryProvider._id,pid)
            values.put(InventoryProvider.name,pname)
            values.put(InventoryProvider.buy_price,bp)
            values.put(InventoryProvider.sell_price,sp)
            values.put(InventoryProvider.supplier,sn)
            values.put(InventoryProvider.quantity,qty)

            try{
            val uri = contentResolver.insert(InventoryProvider.CONTENT_URI,values)
            Toast.makeText(baseContext, "Success", Toast.LENGTH_LONG).show()
                MainActivity.CurrentBalance -= (qty.toInt() * bp.toDouble())
                getSharedPreferences("PREFS", MODE_PRIVATE).edit().putString("CurrentBalance",
                    MainActivity.CurrentBalance.toString()).apply()
            }

            catch (e: IllegalArgumentException)
            {
                Toast.makeText(baseContext, "Missing field or duplicate ID", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }

    }
}